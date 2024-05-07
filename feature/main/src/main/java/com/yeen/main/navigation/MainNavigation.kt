package com.yeen.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yeen.main.MainScreen
import com.yeen.signin.SignInScreen

fun NavController.navigateMain() {
    navigate(MainRoute.route)
}

fun NavGraphBuilder.mainNavGraph(
    onMainClick: (Int) -> Unit
) {
    composable(route = MainRoute.route) {
        MainScreen(onMainClick)
    }
}

object MainRoute {
    const val route = "main"
}