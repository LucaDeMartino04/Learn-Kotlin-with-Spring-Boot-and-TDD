package luca.learn.dataclasses

data class SearchStringFromMutableListWithIndex(
    val mutableList: MutableList<String>,
    val index: Int
)

data class SearchStringFromMutableList(
    val mutableList: MutableList<String>,
    val string: String
)