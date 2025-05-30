package kb24.ehsan.news.screen.articles.component.item

data class ArticleItemUiModel(
    val id: Long,
    val image: String?,
    val title: String,
    val shortBrief: String?,
    val queryName: String,
    val date: String
)