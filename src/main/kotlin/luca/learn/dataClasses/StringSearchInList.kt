package luca.learn.dataClasses

data class IndexedStringSearch(
    val mutableList: MutableList<String>,
    val index: Int
)

data class StringSearchInList(
    val mutableList: MutableList<String>,
    val searchString: String
)
