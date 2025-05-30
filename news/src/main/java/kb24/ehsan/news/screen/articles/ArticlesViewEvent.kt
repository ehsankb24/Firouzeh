package kb24.ehsan.news.screen.articles

sealed interface ArticlesViewEvent {
    data object Retry : ArticlesViewEvent
    data class OnItemClick(val newsId: Long) : ArticlesViewEvent
}