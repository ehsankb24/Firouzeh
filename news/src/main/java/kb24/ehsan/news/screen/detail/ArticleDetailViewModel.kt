package kb24.ehsan.news.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kb24.ehsan.domain.model.ResultState
import kb24.ehsan.domain.usecase.GetArticleDetailUseCase
import kb24.ehsan.news.screen.detail.component.ArticleDetailLayoutUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import javax.inject.Named

@HiltViewModel(assistedFactory = ArticleDetailViewModelFactory::class)
class ArticleDetailViewModel @AssistedInject constructor(
    private val getArticleDetailUseCase: GetArticleDetailUseCase,
    @Named("readable date formatter") private val readableFormatter: SimpleDateFormat,
    @Assisted private val articleId: Long
) : ViewModel() {

    private val _viewState = MutableStateFlow<ArticleDetailViewState>(
        ArticleDetailViewState.Loading
    )
    val viewState = _viewState.asStateFlow()

    init {
        getArticleDetail()
    }

    private fun getArticleDetail() {
        viewModelScope.launch {
            _viewState.emit(
                when (val result = getArticleDetailUseCase(articleId)) {
                    is ResultState.Exception -> ArticleDetailViewState.Error(result.message)
                    is ResultState.Success -> result.data?.run {
                        ArticleDetailViewState.Success(
                            ArticleDetailLayoutUiModel(
                                imageUri = urlToImage,
                                title,
                                content,
                                date = readableFormatter.format(publishedAt),
                                author,
                                sourceName
                            )
                        )
                    } ?: ArticleDetailViewState.Error("there is no news")
                }
            )
        }
    }

    fun onEvent(event: ArticleDetailViewEvent) {
        when (event) {
            ArticleDetailViewEvent.Retry -> {
                _viewState.update {
                    ArticleDetailViewState.Loading
                }
                getArticleDetail()
            }
        }
    }
}