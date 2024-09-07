package org.nextoptech.news.utils


enum class QueryParams(val priority: Int) {
    Microsoft(0),
    Apple(1),
    Google(2),
    Tesla(3);


    companion object {
        private val map = QueryParams.values().associateBy { it.priority }
        infix fun from(value: Int) = map[value]
    }
}

fun <T : Enum<T>> Array<T>.names() = this.map { it.ordinal }



enum class SortByParams {
    Relevancy, Popularity, PublishedAt
}

val sortBy = SortByParams.PublishedAt.name

