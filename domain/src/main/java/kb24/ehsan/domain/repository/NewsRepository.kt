package kb24.ehsan.domain.repository

import kb24.ehsan.domain.model.ArticleDomainModel
import kb24.ehsan.domain.model.ArticleType
import kb24.ehsan.domain.model.ResultState
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface NewsRepository {
    fun getNews(query: ArticleType): Flow<List<ArticleDomainModel>>

    suspend fun refreshNews(
        from: Date,
        to: Date,
        query: ArticleType
    ): ResultState<Unit>

    suspend fun getArticle(id: Long): ResultState<ArticleDomainModel?>
}