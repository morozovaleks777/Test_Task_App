package com.moralex.testtaskapp.presentation.navigation

//import com.example.barnassistant.presentation.screens.channel_list_screen.ChannelListScreen
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.moralex.testtaskapp.presentation.MainViewModel
import com.moralex.testtaskapp.presentation.screens.DetailScreen
import com.moralex.testtaskapp.presentation.screens.MainScreen
import com.moralex.testtaskapp.presentation.screens.SplashScreen

private const val ANIMATION_SPEED = 900

@ExperimentalComposeUiApi
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.SplashScreen.name ){
       composableAnimated(AppScreens.SplashScreen.name) {
          SplashScreen(navController = navController)
        }
        composableAnimated(AppScreens.MainScreen.name) {
            val viewModel = hiltViewModel<MainViewModel>()

            MainScreen(navController = navController,viewModel)
        }
        composableAnimated(
            route = "${AppScreens.DetailScreen.name}?text={factText}",
            arguments = listOf(navArgument("factText") { type = NavType.StringType })
        ) { backStackEntry ->
            val factText = backStackEntry.arguments?.getString("factText")
            DetailScreen(navController = navController, factText = factText ?: "")
        }
    }
}


private fun NavGraphBuilder.composableAnimated(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? = Transitions.SLIDE_IN,
    exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? = Transitions.SLIDE_OUT,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
) {
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        content = content
    )
}

object Transitions {
    val SLIDE_IN: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? = {
        slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Up,
            animationSpec = tween(ANIMATION_SPEED)
        )
    }
    val SLIDE_OUT: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? = {
        slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Down,
            animationSpec = tween(ANIMATION_SPEED)
        )
    }
    val FADE_IN: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? = {
        fadeIn(
            animationSpec = tween(ANIMATION_SPEED)
        )
    }
    val FADE_OUT: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? = {
        fadeOut(
            animationSpec = tween(ANIMATION_SPEED)
        )
    }
}
