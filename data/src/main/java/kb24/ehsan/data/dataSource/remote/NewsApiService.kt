package kb24.ehsan.data.dataSource.remote

import kb24.ehsan.data.model.remote.request.NewsQueries
import kb24.ehsan.data.model.remote.request.NewsSortType
import kb24.ehsan.data.model.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything")
    suspend fun getTopNewsHeadlines(
        @Query("q") keyword: NewsQueries,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("sortBy") sortBy: NewsSortType,
        @Query("page") page: Int,
        @Query("perSize") perSize: Int,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}