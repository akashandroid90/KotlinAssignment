package lib.apidata.response

sealed class Result<T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Error<T>(val exception: Exception) : Result<T>()
}