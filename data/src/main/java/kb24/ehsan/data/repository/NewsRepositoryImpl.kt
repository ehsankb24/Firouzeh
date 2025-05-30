package kb24.ehsan.data.repository

import kb24.ehsan.data.dataSource.local.ArticleDao
import kb24.ehsan.data.dataSource.remote.NewsApiService
import kb24.ehsan.data.model.remote.request.NewsQueries
import kb24.ehsan.data.model.remote.request.NewsSortType
import kb24.ehsan.domain.model.ArticleDomainModel
import kb24.ehsan.domain.model.ArticleType
import kb24.ehsan.domain.model.ResultState
import kb24.ehsan.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date

class NewsRepositoryImpl(
    private val newsApiService: NewsApiService,
    private val articleDao: ArticleDao,
    private val apiKey: String,
    private val remoteDateFormatter: SimpleDateFormat,
    private val mapperDateFormatter: SimpleDateFormat
) : NewsRepository {

    override fun getNews(query: ArticleType): Flow<List<ArticleDomainModel>> {
        val newsQuery = getNewsQuery(query)
        return articleDao.getArticlesByQuery(newsQuery.name).map { articles ->
            articles.map { article ->
                article.toDomain(mapperDateFormatter)
            }
        }
    }

    override suspend fun refreshNews(from: Date, to: Date, query: ArticleType): ResultState<Unit> {
        return refreshNewsWithQuery(from, to, getNewsQuery(query))
    }

    private fun getNewsQuery(query: ArticleType): NewsQueries {
        return when (query) {
            ArticleType.APPLE -> NewsQueries.Apple
            ArticleType.MICROSOFT -> NewsQueries.Microsoft
            ArticleType.GOOGLE -> NewsQueries.Google
            ArticleType.TESLA -> NewsQueries.Tesla
        }
    }

    private suspend fun refreshNewsWithQuery(
        from: Date,
        to: Date,
        keyword: NewsQueries
    ): ResultState<Unit> = withContext(Dispatchers.IO) {
        return@withContext try {
            val newsResponse = newsApiService.getTopNewsHeadlines(
                keyword = keyword,
                from = remoteDateFormatter.format(from),
                to = remoteDateFormatter.format(to),
                sortBy = NewsSortType.publishedAt,
                page = 1,
                perSize = 20,
                apiKey = apiKey
            )
            articleDao.insertArticles(
                newsResponse.articles.map { article ->
                    article.toEntity(keyword)
                }
            )
            ResultState.Success(Unit)
        } catch (e: Exception) {
            // Handle error (e.g., log or notify user)
            e.printStackTrace()
            ResultState.Exception(e.message.toString())
        }
    }

    override suspend fun getArticle(id: Long): ResultState<ArticleDomainModel?> {
        return try {
            ResultState.Success(
                articleDao.getArticleById(id)?.toDomain(mapperDateFormatter)
            )
        } catch (e: Exception) {
            ResultState.Exception(e.message.toString())
        }
    }
}