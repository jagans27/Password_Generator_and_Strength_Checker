package com.jagan.passwordutil.Screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jagan.passwordutil.R

@Composable
fun AppTopBar(appname: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFFFA8B00))
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {


            Image(
                painter = painterResource(id = R.drawable.applogo),
                contentDescription = "Pwd Util",
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 15.dp),
                colorFilter = ColorFilter.tint(Color.White)

            )
            Text(
                text = appname,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

@Preview
@Composable
fun AppTopBarPreview() {
    AppTopBar("Password Generator")
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppTopBarPreview1() {
    AppTopBar("Password Generator")
}
