package kb24.ehsan.news.screen.articles

sealed interface ArticlesSingleAction {
    data class Navigate(val rout: Any) : ArticlesSingleAction
    data class ShowMessage(val message: String) : ArticlesSingleAction
}