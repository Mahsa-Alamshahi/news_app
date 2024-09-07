package org.nextoptech.news.utils

import org.jsoup.Jsoup
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import javax.inject.Inject


class UiUtils @Inject constructor() {


    fun parseHtml(bodyText: String?): String {
        return bodyText?.let { Jsoup.parse(it).text() }.toString()
    }


    fun getTodayDate(): String{
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        val current = formatter.format(date)
        return current
    }


    fun getDaysAgo(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = calendar.time
        val daysAgoDate = formatter.format(date)
        return daysAgoDate
    }


}