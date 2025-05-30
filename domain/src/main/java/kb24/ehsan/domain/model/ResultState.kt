package kb24.ehsan.domain.model

sealed interface ResultState<out T> {
    data class Exception(val message: String) : ResultState<Nothing>
    data class Success<T>(val data: T) : ResultState<T>
}