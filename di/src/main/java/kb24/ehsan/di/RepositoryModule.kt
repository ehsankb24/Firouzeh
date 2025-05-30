package kb24.ehsan.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kb24.ehsan.data.dataSource.local.ArticleDao
import kb24.ehsan.data.dataSource.remote.NewsApiService
import kb24.ehsan.data.repository.NewsRepositoryImpl
import kb24.ehsan.domain.repository.NewsRepository
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideNewsRepository(
        newsApiService: NewsApiService,
        articleDao: ArticleDao,
        @RemoteToken remoteToken: String,
        @Named("remote date formatter") formatter: SimpleDateFormat,
        @Named("domain date formatter") mapperDateFormatter: SimpleDateFormat
    ): NewsRepository {
        return NewsRepositoryImpl(
            newsApiService, articleDao, remoteToken, formatter, mapperDateFormatter
        )
    }

    @Provides
    @Named("remote date formatter")
    fun getRemoteDateFormatter() = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    @Provides
    @Named("domain date formatter")
    fun getDomainDateFormatter() = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

    @Provides
    @Named("readable date formatter")
    fun getReadableFormatter() = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
}