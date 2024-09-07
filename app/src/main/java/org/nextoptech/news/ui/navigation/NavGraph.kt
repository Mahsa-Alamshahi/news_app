package org.nextoptech.news.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.nextoptech.news.common.fromJson
import org.nextoptech.news.ui.news_details.NewsDetailsScreenRoute
import org.nextoptech.news.ui.news_list.NewsListScreenRoute
import org.nextoptech.news.common.AppConstant.NEWS_DETAILS_NAVIGATION_ARGUMENT_KEY
import org.nextoptech.news.data.data_source.local.NewsEntity
import org.nextoptech.news.data.data_source.remote.dto.Article

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.NewsList.route) {
        newsListRoute(navController)
        newsDetailsRoute(navController)
    }
}


fun NavGraphBuilder.newsListRoute(navController: NavController) {
    composable(
        route = Screen.NewsList.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(700)
            )
        },
    ) {
        NewsListScreenRoute() { news ->
            navController.navigate(Screen.NewsDetails.passNews(news))
        }
    }
}


fun NavGraphBuilder.newsDetailsRoute(navController: NavController) {
    composable(
        route = Screen.NewsDetails.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(700)
            )
        },
        arguments = listOf(navArgument(NEWS_DETAILS_NAVIGATION_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->

        navBackStackEntry.arguments?.getString(NEWS_DETAILS_NAVIGATION_ARGUMENT_KEY)
            ?.let { jsonString ->
                val news = jsonString.fromJson(NewsEntity::class.java)
                NewsDetailsScreenRoute(news)
            }
    }
}

