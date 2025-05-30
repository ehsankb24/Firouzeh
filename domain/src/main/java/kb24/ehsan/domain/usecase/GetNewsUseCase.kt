package kb24.ehsan.domain.usecase

import kb24.ehsan.domain.model.ArticleDomainModel
import kb24.ehsan.domain.model.ArticleType
import kb24.ehsan.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetNewsUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<List<ArticleDomainModel>> {
        val microsoftNews = repository.getNews(ArticleType.MICROSOFT)
        val appleNews = repository.getNews(ArticleType.APPLE)
        val googleNews = repository.getNews(ArticleType.GOOGLE)
        val teslaNews = repository.getNews(ArticleType.TESLA)

        return combine(microsoftNews, appleNews, googleNews, teslaNews) { microsoft, apple, google, tesla ->
            val allNews = mutableListOf<ArticleDomainModel>()
            
            // Get the maximum size among all lists
            val maxSize = maxOf(microsoft.size, apple.size, google.size, tesla.size)
            
            // Add items in the specified order
            for (i in 0 until maxSize) {
                if (i < microsoft.size) allNews.add(microsoft[i])
                if (i < apple.size) allNews.add(apple[i])
                if (i < google.size) allNews.add(google[i])
                if (i < tesla.size) allNews.add(tesla[i])
            }
            
            allNews
        }
    }
}