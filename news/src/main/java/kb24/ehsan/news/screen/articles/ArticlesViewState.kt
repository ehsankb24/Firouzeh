package kb24.ehsan.news.screen.articles

import kb24.ehsan.news.screen.articles.component.item.ArticleItemUiModel

sealed interface ArticlesViewState {
    data object Loading : ArticlesViewState
    data class Error(val message: String) : ArticlesViewState
    sealed class Success : ArticlesViewState {
        data object Empty : Success()
        data class Items(val articles: List<ArticleItemUiModel>) : Success()
    }
}