package com.yeen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.yeen.login.navigation.LoginRoute
import com.yeen.login.navigation.loginNavGraph
import com.yeen.login.navigation.navigateLogin
import com.yeen.main.navigation.MainRoute
import com.yeen.main.navigation.mainNavGraph
import com.yeen.main.navigation.navigateMain
import com.yeen.signin.navigation.navigateSignIn
import com.yeen.signin.navigation.signInNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = LoginRoute.route
                ) {
                    loginNavGraph(
                        onSignInClick = { navController.navigateSignIn() },
                        onMainClick = { navController.navigateMain()}
                    )
                    signInNavGraph(
                        onLoginClick = { navController.navigateLogin() }
                    )
                    mainNavGraph(
                        onMainClick = { navController.navigateMain() }
                    )

                }
            }
        }
    }
}