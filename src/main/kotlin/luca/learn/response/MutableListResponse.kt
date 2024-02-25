package luca.learn.response

sealed class MutableListResponse {
    data class Success(val list: MutableList<String>) : MutableListResponse()
    data class Error(val message: String) : MutableListResponse()
}