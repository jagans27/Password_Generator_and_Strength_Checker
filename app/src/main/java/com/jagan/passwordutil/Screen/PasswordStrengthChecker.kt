package com.jagan.passwordutil.Screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jagan.passwordutil.Util.SharedViewModel


@Composable
fun PasswordStrengthChecker(
    context: Context
) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current


    val viewModel = viewModel<SharedViewModel>()
    val strength by viewModel.strength.collectAsState()

    var password by remember {
        mutableStateOf("")
    }

    Column {
        AppTopBar(appname = "Password Strength Checker")
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isSystemInDarkTheme()) Color(0xFF2D2F31) else Color.White),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Check Password Strength",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = if (isSystemInDarkTheme()) Color.White else Color(0xFF2D2F31)
                )
                Spacer(modifier = Modifier.height(30.dp))
                TextField(
                    shape = RoundedCornerShape(50.dp),

                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFFA8B00),
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),

                    placeholder = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .align(alignment = Alignment.CenterHorizontally),
                            text = "Enter Password",
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    },

                    value = password,
                    onValueChange = {
                        if (it.length <= 8 && !it.contains("\n") && !it.contains(" ")) {
                            password = it
                            viewModel.checkPasswordStrength(password = password)
                        }
                    },
                    textStyle = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    val openDialog = remember { mutableStateOf(false) }

                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .width(150.dp)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFFA8B00))
                            .clickable {
                                openDialog.value = true
                            }

                    ) {

                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "Check",
                            color = Color.White,
                            style = MaterialTheme.typography.h6
                        )
                    }

                    /* Alert Box */

                    if (openDialog.value) {
                        AlertDialog(
                            backgroundColor = Color(0xFFFA8B00),
                            onDismissRequest = {
                                openDialog.value = false;
                            },
                            title = {
                                Text(
                                    text = "⚠ Your Password Strength",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            },
                            text = {
                                Text(
                                    text = strength,
                                    fontStyle = FontStyle.Italic,
                                    fontSize = 14.sp
                                )

                            },
                            confirmButton = {
                                Button(

                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color(
                                            0xFF2D2F31
                                        )
                                    ),

                                    onClick = {
                                        openDialog.value = false;
                                    }
                                ) {
                                    Text("Okay", color = Color.White)
                                }
                            }

                        )
                    }

                    /* End Alert Box */

                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .width(150.dp)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFFA8B00))
                            .clickable {
                                // clipboard past
                                clipboardManager.getText()?.text?.let {
                                    if (it.length <= 8 && !it.contains("\n") && !it.contains(" ")) {
                                        password = it
                                        viewModel.checkPasswordStrength(password = password)
                                        Toast
                                            .makeText(
                                                context,
                                                "Successfully Pasted",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()

                                    } else {
                                        Toast
                                            .makeText(
                                                context,
                                                "Invalid Password",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                                }
                            }
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "Past",
                            color = Color.White,
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PasswordStrengthCheckerPreview(){
    PasswordStrengthChecker(LocalContext.current)
}

