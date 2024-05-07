package com.yeen.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yeen.login.LoginScreen

fun NavController.navigateLogin() {
    navigate(LoginRoute.route)
}

fun NavGraphBuilder.loginNavGraph(
    onSignInClick: (Int) -> Unit,
    onMainClick: (Int) -> Unit
) {
    composable(route = LoginRoute.route) {
        LoginScreen(
            onSignInClick = onSignInClick,
            onMainClick = onMainClick
        )
    }
}

object LoginRoute {
    const val route = "login"
}