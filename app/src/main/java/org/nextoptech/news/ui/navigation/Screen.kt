package org.nextoptech.news.ui.navigation

import org.nextoptech.news.common.AppConstant.NEWS_DETAILS_NAVIGATION_ARGUMENT_KEY

sealed class Screen(val route: String) {


    object NewsList : Screen(route = "news_list_screen")

    object NewsDetails :
        Screen(route = "news_details_screen?$NEWS_DETAILS_NAVIGATION_ARGUMENT_KEY={$NEWS_DETAILS_NAVIGATION_ARGUMENT_KEY}") {
        fun passNews(article: String?) =
            "news_details_screen?$NEWS_DETAILS_NAVIGATION_ARGUMENT_KEY=$article"
    }
}