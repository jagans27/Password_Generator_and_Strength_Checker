package com.jagan.passwordutil.Navigation

sealed class Screen(val route:String){
    object MainScreen: Screen("main_screen")
    object Splash: Screen("splash_screen")
}
