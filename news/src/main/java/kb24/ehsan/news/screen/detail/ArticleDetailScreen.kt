package kb24.ehsan.news.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kb24.ehsan.news.R
import kb24.ehsan.news.screen.detail.component.ArticleDetailLoadingLayout
import kb24.ehsan.news.screen.detail.component.ArticleDetailSuccessLayout
import kb24.ehsan.news.screen.detail.component.ArticleDetailSuccessLayoutPreviewParameter
import kb24.ehsan.ui.component.FailedComponent
import kb24.ehsan.ui.theme.FirouzehTheme
import kb24.ehsan.ui.theme.PreviewApp

@Composable
fun ArticleDetailScreen(
    viewModel: ArticleDetailViewModel,
    navigateBack: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    ArticleDetailLayout(viewState, navigateBack, viewModel::onEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArticleDetailLayout(
    viewState: ArticleDetailViewState,
    navigateBack: () -> Unit,
    onEvent: (ArticleDetailViewEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
                    .padding(bottom = 16.dp),
                title = {
                    Text(
                        text = stringResource(R.string.article_detail_screen_title),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
                    containerColor = Color.Transparent
                ),
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        when (viewState) {
            is ArticleDetailViewState.Error -> FailedComponent(
                modifier = modifier.fillMaxSize(),
                message = viewState.message,
                onAction = { onEvent(ArticleDetailViewEvent.Retry) }
            )

            ArticleDetailViewState.Loading -> ArticleDetailLoadingLayout(modifier = modifier)
            is ArticleDetailViewState.Success -> ArticleDetailSuccessLayout(
                modifier = modifier,
                data = viewState.data
            )
        }
    }
}

@PreviewApp
@Composable
private fun ArticleDetailLayoutPreview(
    @PreviewParameter(ArticleDetailLayoutPreviewParameter::class) viewState: ArticleDetailViewState
) {
    FirouzehTheme {
        ArticleDetailLayout(
            viewState = viewState,
            navigateBack = {},
            onEvent = {}
        )
    }
}

private class ArticleDetailLayoutPreviewParameter :
    PreviewParameterProvider<ArticleDetailViewState> {
    override val values: Sequence<ArticleDetailViewState>
        get() = sequenceOf(
            ArticleDetailViewState.Loading,
            ArticleDetailViewState.Error("message"),
            ArticleDetailViewState.Success(data = ArticleDetailSuccessLayoutPreviewParameter().values.first())
        )
}
