package kb24.ehsan.news.screen.articles.component

import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kb24.ehsan.news.screen.articles.component.item.ArticleItem
import kb24.ehsan.news.screen.articles.component.item.ArticleItemPreviewParameter
import kb24.ehsan.news.screen.articles.component.item.ArticleItemUiModel
import kb24.ehsan.ui.theme.FirouzehTheme

@Composable
internal fun ArticleListLayout(
    modifier: Modifier = Modifier,
    articles: List<ArticleItemUiModel>,
    onItemClick: (articleId: Long) -> Unit
) {
    ArticleListComponent(
        modifier = modifier
    ) {
        items(
            items = articles,
            key = { it.id }
        ) {
            ArticleItem(
                data = it,
                onItemClick = { onItemClick(it.id) },
            )
        }
    }
}

@Preview
@Composable
private fun ArticleListLayoutPreview(
    @PreviewParameter(ArticleListLayoutPreviewParameter::class) articles: List<ArticleItemUiModel>
) {
    FirouzehTheme {
        ArticleListLayout(
            articles = articles,
            onItemClick = {}
        )
    }
}

internal class ArticleListLayoutPreviewParameter :
    PreviewParameterProvider<List<ArticleItemUiModel>> {
    override val values: Sequence<List<ArticleItemUiModel>>
        get() = sequenceOf(
            (0..2).map { ArticleItemPreviewParameter().values.first() }
        )
}