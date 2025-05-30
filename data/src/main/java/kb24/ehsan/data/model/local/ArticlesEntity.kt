package kb24.ehsan.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import kb24.ehsan.data.model.remote.request.NewsQueries
import kb24.ehsan.domain.model.ArticleDomainModel
import kb24.ehsan.domain.model.ArticleType
import java.text.SimpleDateFormat
import java.util.Date

@Entity(tableName = "articles")
data class ArticlesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String,
    val sourceId: String?,
    val sourceName: String,
    val keyword: NewsQueries
) {
    fun toDomain(dateFormatter: SimpleDateFormat) = ArticleDomainModel(
        id,
        author,
        title,
        description,
        url,
        urlToImage,
        publishedAt = dateFormatter.parse(publishedAt) ?: Date(),
        content,
        sourceId,
        sourceName,
        mapQueryType(keyword)
    )

    private fun mapQueryType(query: NewsQueries): ArticleType = when (query) {
        NewsQueries.Microsoft -> ArticleType.MICROSOFT
        NewsQueries.Apple -> ArticleType.APPLE
        NewsQueries.Google -> ArticleType.GOOGLE
        NewsQueries.Tesla -> ArticleType.TESLA
    }
}