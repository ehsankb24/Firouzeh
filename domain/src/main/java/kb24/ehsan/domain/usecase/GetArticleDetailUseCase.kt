package kb24.ehsan.domain.usecase

import kb24.ehsan.domain.model.ArticleDomainModel
import kb24.ehsan.domain.model.ResultState
import kb24.ehsan.domain.repository.NewsRepository

class GetArticleDetailUseCase(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(id: Long): ResultState<ArticleDomainModel?> {
        return repository.getArticle(id)
    }
}