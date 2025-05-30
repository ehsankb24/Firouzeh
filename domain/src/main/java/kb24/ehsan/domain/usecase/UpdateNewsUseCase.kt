package kb24.ehsan.domain.usecase

import kb24.ehsan.domain.model.ArticleType
import kb24.ehsan.domain.model.ResultState
import kb24.ehsan.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlin.coroutines.coroutineContext

class UpdateNewsUseCase(
    private val repository: NewsRepository,
    private val calendar: Calendar
) {
    suspend operator fun invoke(): List<ResultState<Unit>> {
        val toDate = calendar.time
        val fromDate = calendar.apply {
            add(Calendar.DATE, -1)
        }.time
        val result = arrayListOf<ResultState<Unit>>()
        return with(CoroutineScope(coroutineContext)) {
            ArticleType.entries.forEach {
                launch {
                    result.add(repository.refreshNews(from = fromDate, to = toDate, query = it))
                }
            }
            return@with result
        }
    }
}