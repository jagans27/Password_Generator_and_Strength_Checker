package com.jagan.passwordutil.Screen

import android.content.Context
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.jagan.passwordutil.Navigation.BottomNav.BottomNavGraph
import com.jagan.passwordutil.Navigation.BottomNavgation.BottomNavBar
import com.jagan.passwordutil.Navigation.BottomNavgation.BottomNavItem

@Composable
fun MainScreen(
    context: Context
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                items = listOf(
                    BottomNavItem(
                        name = "Password Generator",
                        route = "password_generator_screen",
                        icon = Icons.Default.Lock
                    ),
                    BottomNavItem(
                        name = "Password Strength Checker",
                        route = "password_strength_checker_screen",
                        icon = Icons.Default.Info
                    )
                ),

                navController = navController,
                onItemClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            )
        }
    ) {
        BottomNavGraph(navController = navController,context=context)
    }
}