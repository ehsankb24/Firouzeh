package kb24.ehsan.news.screen.detail

import dagger.assisted.AssistedFactory

@AssistedFactory
interface ArticleDetailViewModelFactory {
    fun create(articleId: Long): ArticleDetailViewModel
}