package com.jagan.passwordutil

import android.os.Bundle
import android.view.WindowInsets.Side
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.jagan.passwordutil.Navigation.NavGraph
import com.jagan.passwordutil.ui.theme.PasswordUtilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordUtilTheme {

                val context = LocalContext.current

                val navController = rememberNavController()
                NavGraph(navController = navController,context=context)
            }
        }
    }
}






