package com.jagan.passwordutil.Screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jagan.passwordutil.R
import com.jagan.passwordutil.Util.SharedViewModel


@Composable
fun PasswordGeneratorScreen(
    context: Context
) {

    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    /* ViewModel */
    val viewModel = viewModel<SharedViewModel>()
    val password by viewModel.password.collectAsState()

    Column {
        AppTopBar(appname = "Password Generator")
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isSystemInDarkTheme()) Color(0xFF2D2F31) else Color.White),
            contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Generated Password",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = if (isSystemInDarkTheme()) Color.White else Color(0xFF2D2F31)
                )
                Spacer(modifier = Modifier.height(30.dp))
                SelectionContainer() {
                    Text(
                        text = password,
                        fontSize = 50.sp,
                        color = if (isSystemInDarkTheme()) Color.White else Color(0xFF2D2F31)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .width(150.dp)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFFA8B00))
                            .clickable {
                                /*sharedViewModel.generatePassword { data->
                                    password.value = data
                                }*/
                                viewModel.generatePassword()
                                Toast
                                    .makeText(context, "Password Generated", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "Generate",
                            color = Color.White,
                            style = MaterialTheme.typography.h6
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .width(150.dp)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFFA8B00))
                            .clickable {
                                // clipboard copy
                                clipboardManager.setText(AnnotatedString(password))
                                Toast
                                    .makeText(context, "Successfully Copied", Toast.LENGTH_SHORT)
                                    .show()
                            }

                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "Copy",
                            color = Color.White,
                            style = MaterialTheme.typography.h6
                        )
                    }
                }

            }

        }
    }
}


