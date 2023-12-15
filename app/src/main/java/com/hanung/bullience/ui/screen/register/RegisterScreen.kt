package com.hanung.bullience.ui.screen.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    navigateToLogin: () -> Unit,
) {
    RegisterContent(
        modifier = modifier,
        onBackClick = navigateBack,
        onLoginCLick = navigateToLogin,
        onRegisterClick = navigateToLogin
    )
}

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onLoginCLick: () -> Unit,
    onRegisterClick: () -> Unit,
){
    val context = LocalContext.current
    val name = remember {
        mutableStateOf(TextFieldValue())
    }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val countryCode = remember { mutableStateOf(TextFieldValue()) }
    val mobileNo = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val confirmPassword = remember { mutableStateOf(TextFieldValue()) }

    val nameErrorState = remember { mutableStateOf(false) }
    val emailErrorState = remember { mutableStateOf(false) }
    val passwordErrorState = remember { mutableStateOf(false) }
    val confirmPasswordErrorState = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Daftar Akun",
            style = MaterialTheme.typography.h1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 64.dp, bottom = 0.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
        )
        Text(
            text = "Isi data diri kamu di bawah dengan benar",
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.size(16.dp))
        Text(text = "Nama Lengkap", fontWeight = FontWeight.Bold ,fontSize = 14.sp, modifier = Modifier.padding(start = 16.dp, top = 64.dp))
        OutlinedTextField(
            value = name.value,
            onValueChange = {
                if (nameErrorState.value) {
                    nameErrorState.value = false
                }
                name.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 0.dp),
            isError = nameErrorState.value,
            label = {
                Text(text = "Name*")
            },
        )
        if (nameErrorState.value) {
            Text(text = "Required", color = Color.Red)
        }
        Spacer(Modifier.size(16.dp))

        Text(text = "Email", fontWeight = FontWeight.Bold , fontSize = 14.sp, modifier = Modifier.padding(start = 16.dp))
        OutlinedTextField(
            value = email.value,
            onValueChange = {
                if (emailErrorState.value) {
                    emailErrorState.value = false
                }
                email.value = it
            },

            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 0.dp),
            isError = emailErrorState.value,
            label = {
                Text(text = "Email*")
            },
        )
        if (emailErrorState.value) {
            Text(text = "Required", color = Color.Red)
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "Password",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        val passwordVisibility = remember { mutableStateOf(true) }
        OutlinedTextField(
            value = password.value,
            onValueChange = {
                if (passwordErrorState.value) {
                    passwordErrorState.value = false
                }
                password.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 0.dp),
            label = {
                Text(text = "Password*")
            },
            isError = passwordErrorState.value,
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(
                        imageVector = if (passwordVisibility.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = "visibility",
                        tint = Color.Blue
                    )
                }
            },
            visualTransformation = if (passwordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None
        )
        if (passwordErrorState.value) {
            Text(text = "Required", color = Color.Red)
        }

        Spacer(Modifier.size(16.dp))
        Text(text = "Confirm Password", fontWeight = FontWeight.Bold ,fontSize = 14.sp, modifier = Modifier.padding(start = 16.dp))
        val cPasswordVisibility = remember { mutableStateOf(true) }
        OutlinedTextField(
            value = confirmPassword.value,
            onValueChange = {
                if (confirmPasswordErrorState.value) {
                    confirmPasswordErrorState.value = false
                }
                confirmPassword.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 0.dp),
            isError = confirmPasswordErrorState.value,
            label = {
                Text(text = "Confirm Password*")
            },
            trailingIcon = {
                IconButton(onClick = {
                    cPasswordVisibility.value = !cPasswordVisibility.value
                }) {
                    Icon(
                        imageVector = if (cPasswordVisibility.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = "visibility",
                        tint = Color.Blue
                    )
                }
            },
            visualTransformation = if (cPasswordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None
        )
        if (confirmPasswordErrorState.value) {
            val msg = if (confirmPassword.value.text.isEmpty()) {
                "Required"
            } else if (confirmPassword.value.text != password.value.text) {
                "Password not matching"
            } else {
                ""
            }
            Text(text = msg, color = Color.Red)
        }
        Spacer(Modifier.size(16.dp))
        Button(
            onClick = {
                when {
                    name.value.text.isEmpty() -> {
                        nameErrorState.value = true
                    }
                    email.value.text.isEmpty() -> {
                        emailErrorState.value = true
                    }
                    password.value.text.isEmpty() -> {
                        passwordErrorState.value = true
                    }
                    confirmPassword.value.text.isEmpty() -> {
                        confirmPasswordErrorState.value = true
                    }
                    confirmPassword.value.text != password.value.text -> {
                        confirmPasswordErrorState.value = true
                    }
                    else -> {
                        Toast.makeText(
                            context,
                            "Registered successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        onRegisterClick()
                    }
                }
            },
            content = {
                Text(text = "Daftar Akun", color = Color.White)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 0.dp),
            colors = ButtonDefaults.buttonColors(Color.Blue)
        )
        Spacer(Modifier.size(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Sudah punya akun? ",
                color = Color.Black
            )
            Text(
                modifier = Modifier
                    .clickable {
                        onLoginCLick()
                    },
                text = "Login",
                color = Color.Blue)

        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        navigateBack = {},
        navigateToLogin = {},
    )
}