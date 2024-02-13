package com.moralex.testtaskapp.presentation.navigation

import java.lang.IllegalArgumentException

enum class AppScreens {
    SplashScreen,
    DetailScreen,
    MainScreen;

    companion object {
        fun fromRoute(route: String?): AppScreens
                = when(route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            MainScreen.name -> MainScreen
            DetailScreen.name -> DetailScreen
            null -> MainScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}