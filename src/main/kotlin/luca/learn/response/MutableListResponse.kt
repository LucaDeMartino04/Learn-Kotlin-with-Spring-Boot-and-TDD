package luca.learn.response

sealed class MutableListResponse {
    data class Success(val list: MutableList<String>) : MutableListResponse()
    data class Error(val message: String) : MutableListResponse()
}

sealed class MutableListSearchByValueResponse {
    data class Success(val pairList: List<Pair<Int, String>>) : MutableListSearchByValueResponse()

    data class Error(val message: String) : MutableListSearchByValueResponse()
}