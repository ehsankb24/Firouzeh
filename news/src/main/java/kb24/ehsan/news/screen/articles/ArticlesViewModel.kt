package kb24.ehsan.news.screen.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kb24.ehsan.domain.model.ResultState
import kb24.ehsan.domain.usecase.GetNewsUseCase
import kb24.ehsan.domain.usecase.UpdateNewsUseCase
import kb24.ehsan.news.navigation.ArticleDetailRout
import kb24.ehsan.news.screen.articles.component.item.ArticleItemUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.text.SimpleDateFormat
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    @Named("readable date formatter") private val readableFormatter: SimpleDateFormat,
    getNewsUseCase: GetNewsUseCase,
    updateNewsUseCase: UpdateNewsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<ArticlesViewState>(
        ArticlesViewState.Loading
    )
    val viewState = _viewState.asStateFlow()

    private val _singleAction = MutableSharedFlow<ArticlesSingleAction>()
    val singleAction = _singleAction.asSharedFlow()

    private val newsStream = getNewsUseCase().catch {
        it.printStackTrace()
        _viewState.emit(ArticlesViewState.Error(it.message.orEmpty()))
    }

    init {
        observedNewsStream()
        viewModelScope.launch {
            val result = updateNewsUseCase()
            result.forEach {
                when (it) {
                    is ResultState.Exception -> _singleAction.emit(
                        ArticlesSingleAction.ShowMessage(
                            it.message
                        )
                    )

                    is ResultState.Success -> Unit
                }
            }
        }
    }

    fun onEvent(event: ArticlesViewEvent) {
        when (event) {
            is ArticlesViewEvent.OnItemClick -> navigateToDetail(event.newsId)
            ArticlesViewEvent.Retry -> retryGetNews()
        }
    }

    private fun retryGetNews() {
        newsStream.retry { e ->
            e.printStackTrace()
            (e is IOException).also {
                if (it) {
                    _viewState.emit(ArticlesViewState.Loading)
                    delay(1000)
                }
            }
        }
    }

    private fun navigateToDetail(newsId: Long) {
        viewModelScope.launch {
            _singleAction.emit(ArticlesSingleAction.Navigate(ArticleDetailRout(newsId)))
        }
    }

    private fun observedNewsStream() = viewModelScope.launch {
        newsStream.collect { news ->
            _viewState.update {
                if (news.isEmpty()) {
                    ArticlesViewState.Success.Empty
                } else {
                    ArticlesViewState.Success.Items(
                        news.map { article ->
                            with(article) {
                                ArticleItemUiModel(
                                    id = id,
                                    image = urlToImage,
                                    title = title,
                                    shortBrief = description,
                                    queryName = article.query.name,
                                    date = readableFormatter.format(article.publishedAt)
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}