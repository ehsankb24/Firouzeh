package kb24.ehsan.data.dataSource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kb24.ehsan.data.model.local.ArticlesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(news: List<ArticlesEntity>)

    @Query("SELECT * FROM articles WHERE keyword = :query ORDER BY publishedAt DESC")
    fun getArticlesByQuery(query: String): Flow<List<ArticlesEntity>>

    @Query("SELECT * FROM articles WHERE id = :id")
    suspend fun getArticleById(id: Long): ArticlesEntity?
}