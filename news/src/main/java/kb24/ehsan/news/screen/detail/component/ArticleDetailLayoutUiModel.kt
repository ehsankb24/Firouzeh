package kb24.ehsan.news.screen.detail.component

data class ArticleDetailLayoutUiModel(
    val imageUri: String?,
    val title: String,
    val content: String,
    val date: String,
    val author: String?,
    val source: String
)