package kb24.ehsan.news.screen.detail

sealed interface ArticleDetailViewEvent {
    data object Retry : ArticleDetailViewEvent
}