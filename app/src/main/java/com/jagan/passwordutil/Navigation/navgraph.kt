package com.jagan.passwordutil.Navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jagan.passwordutil.Screen.AnimatedSplashScreen
import com.jagan.passwordutil.Screen.MainScreen
import com.jagan.passwordutil.Screen.PasswordGeneratorScreen
import com.jagan.passwordutil.Screen.PasswordStrengthChecker

@Composable
fun NavGraph(
    navController: NavHostController,
    context: Context
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen(navController)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(context)
        }
    }
}