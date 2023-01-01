package com.jagan.passwordutil.Screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jagan.passwordutil.R
import com.jagan.passwordutil.Navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavController) {
    var startAnimation by remember { mutableStateOf(false) }
    var alphaAnim =
        animateFloatAsState(
            targetValue = if (startAnimation) 1f else 0f,
            animationSpec = tween(
                durationMillis = 2000
            )
        )
    LaunchedEffect(key1 = true ){
        startAnimation = true
        delay(2000)
        navController.popBackStack()
        navController.navigate(Screen.MainScreen.route)
    }
    Splash(alphaAnim.value)
}

@Composable
fun Splash(alpha:Float) {
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
            .fillMaxSize()
            .alpha(alpha = alpha)
        ,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.applogo),
                contentDescription = "Logo Icon",
                modifier = Modifier.size(150.dp),
            )
            Text(
                text = "Pwd Util",
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        }
    }
}



















