package kb24.ehsan.data.dataSource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import kb24.ehsan.data.model.local.ArticlesEntity

@Database(
    entities = [ArticlesEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}