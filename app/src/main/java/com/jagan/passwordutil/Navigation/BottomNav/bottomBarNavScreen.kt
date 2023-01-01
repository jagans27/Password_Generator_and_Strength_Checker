package com.jagan.passwordutil.Navigation.BottomNav


sealed class BottomBarNavScreen(val route:String){
    object PasswordGenerator: BottomBarNavScreen("password_generator_screen")
    object PasswordStrengthChecker: BottomBarNavScreen("password_strength_checker_screen")
}
