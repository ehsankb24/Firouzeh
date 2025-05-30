package kb24.ehsan.news.screen.detail

import kb24.ehsan.news.screen.detail.component.ArticleDetailLayoutUiModel

sealed interface ArticleDetailViewState {
    data object Loading : ArticleDetailViewState
    data class Error(val message: String) : ArticleDetailViewState
    data class Success(val data: ArticleDetailLayoutUiModel) : ArticleDetailViewState
}