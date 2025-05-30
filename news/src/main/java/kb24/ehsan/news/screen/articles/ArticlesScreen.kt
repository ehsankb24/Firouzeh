package kb24.ehsan.news.screen.articles

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kb24.ehsan.news.R
import kb24.ehsan.news.screen.articles.component.ArticleListLayout
import kb24.ehsan.news.screen.articles.component.ArticleListLayoutPreviewParameter
import kb24.ehsan.news.screen.articles.component.ArticlesLoadingLayout
import kb24.ehsan.ui.CollectSharedFlow
import kb24.ehsan.ui.component.EmptyComponent
import kb24.ehsan.ui.component.FailedComponent
import kb24.ehsan.ui.theme.FirouzehTheme
import kb24.ehsan.ui.theme.PreviewApp
import kb24.ehsan.ui.R as uiR

@Composable
fun ArticlesScreen(
    viewModel: ArticlesViewModel,
    navigateNext: (rout: Any) -> Unit
) {
    val context = LocalContext.current
    viewModel.singleAction.CollectSharedFlow {
        when (it) {
            is ArticlesSingleAction.Navigate -> navigateNext(it.rout)
            is ArticlesSingleAction.ShowMessage -> Toast.makeText(
                context,
                it.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    ArticlesLayout(viewState, viewModel::onEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArticlesLayout(
    viewState: ArticlesViewState,
    onEvent: (event: ArticlesViewEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
                    .padding(bottom = 16.dp)
            ) {
                CenterAlignedTopAppBar(
                    modifier = Modifier,
                    title = {
                        Text(
                            text = stringResource(R.string.articles_screen_title),
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
                        containerColor = Color.Transparent
                    )
                )
            }
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        when (viewState) {
            is ArticlesViewState.Error -> FailedComponent(
                modifier = modifier.fillMaxSize(),
                message = viewState.message,
                onAction = { onEvent(ArticlesViewEvent.Retry) }
            )

            ArticlesViewState.Loading -> ArticlesLoadingLayout(modifier)
            is ArticlesViewState.Success.Empty -> EmptyComponent(
                modifier = modifier.fillMaxSize(),
                imageRes = uiR.drawable.ic_launcher_foreground,
                messageRes = R.string.articles_screen_empty_news_list_message
            )

            is ArticlesViewState.Success.Items -> ArticleListLayout(
                modifier = modifier,
                articles = viewState.articles,
                onItemClick = {
                    onEvent(ArticlesViewEvent.OnItemClick(it))
                }
            )
        }
    }
}

@PreviewApp
@Composable
private fun ArticlesLayoutPreview(
    @PreviewParameter(ArticlesLayoutPreviewParameter::class) viewState: ArticlesViewState
) {
    FirouzehTheme {
        ArticlesLayout(viewState, onEvent = {})
    }
}

private class ArticlesLayoutPreviewParameter : PreviewParameterProvider<ArticlesViewState> {
    override val values: Sequence<ArticlesViewState>
        get() = sequenceOf(
            ArticlesViewState.Loading,
            ArticlesViewState.Error("message"),
            ArticlesViewState.Success.Empty,
            ArticlesViewState.Success.Items(
                articles = ArticleListLayoutPreviewParameter().values.first()
            )
        )
}