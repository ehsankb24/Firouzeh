package kb24.ehsan.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kb24.ehsan.domain.repository.NewsRepository
import kb24.ehsan.domain.usecase.GetArticleDetailUseCase
import kb24.ehsan.domain.usecase.GetNewsUseCase
import kb24.ehsan.domain.usecase.UpdateNewsUseCase
import java.util.Calendar

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun getNewsUseCaseProvider(repository: NewsRepository) = GetNewsUseCase(repository)

    @Provides
    fun getArticleDetailUseCaseProvider(
        repository: NewsRepository
    ) = GetArticleDetailUseCase(repository)

    @Provides
    fun updateNewsUseCaseProvider(
        repository: NewsRepository,
        calender: Calendar
    ) = UpdateNewsUseCase(repository, calender)

    @Provides
    fun getCalender(): Calendar = Calendar.getInstance()
}