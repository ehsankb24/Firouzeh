package kb24.ehsan.data.model.remote.response

import kb24.ehsan.data.model.local.ArticlesEntity
import kb24.ehsan.data.model.remote.request.NewsQueries
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    @SerialName("source") val source: SourceResponse,
    @SerialName("author") val author: String?,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String?,
    @SerialName("url") val url: String,
    @SerialName("urlToImage") val urlToImage: String?,
    @SerialName("publishedAt") val publishedAt: String,
    @SerialName("content") val content: String
) {
    fun toEntity(query: NewsQueries) = ArticlesEntity(
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
        sourceId = source.id,
        sourceName = source.name,
        keyword = query
    )
}