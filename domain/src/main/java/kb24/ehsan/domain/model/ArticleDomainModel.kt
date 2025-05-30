package kb24.ehsan.domain.model

import java.util.Date

data class ArticleDomainModel(
    val id: Long = 0,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: Date,
    val content: String,
    val sourceId: String?,
    val sourceName: String,
    val query: ArticleType
)