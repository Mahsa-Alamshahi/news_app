package org.nextoptech.news

import org.junit.Test

import org.junit.Assert.*
import org.nextoptech.news.utils.QueryParams

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getQueryParamsValues() {
        val queryList = mutableListOf<QueryParams>()
        QueryParams.entries.forEach {
            queryList.add(it)
        }

        assertEquals(
            mutableListOf(
                QueryParams.Apple,
                QueryParams.Microsoft,
                QueryParams.Tesla,
                QueryParams.Google
            ), queryList
        )
    }
}