package kb24.ehsan.news.screen.articles.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kb24.ehsan.news.screen.articles.component.item.ArticleLoadingItem
import kb24.ehsan.ui.theme.FirouzehTheme

@Composable
internal fun ArticlesLoadingLayout(
    modifier: Modifier = Modifier
) {
    ArticleListComponent(modifier = modifier) {
        items(3) {
            ArticleLoadingItem()
        }
    }
}

@Preview
@Composable
private fun ArticlesLoadingLayoutPreview() {
    FirouzehTheme {
        ArticlesLoadingLayout()
    }
}