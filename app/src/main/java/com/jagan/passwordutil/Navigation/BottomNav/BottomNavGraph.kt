package com.jagan.passwordutil.Navigation.BottomNav

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jagan.passwordutil.Navigation.Screen
import com.jagan.passwordutil.Screen.AnimatedSplashScreen
import com.jagan.passwordutil.Screen.PasswordGeneratorScreen
import com.jagan.passwordutil.Screen.PasswordStrengthChecker

@Composable
fun BottomNavGraph(navController: NavHostController, context: Context) {
    NavHost(
        navController = navController,
        startDestination = BottomBarNavScreen.PasswordGenerator.route
    ) {

        composable(route = BottomBarNavScreen.PasswordGenerator.route) {
            PasswordGeneratorScreen(context)
        }

        composable(route = BottomBarNavScreen.PasswordStrengthChecker.route) {
            PasswordStrengthChecker(context)
        }

    }
}