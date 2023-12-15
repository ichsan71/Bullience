package com.hanung.bullience.ui.screen.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.Visibility
import androidx.navigation.NavController



@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit
) {
    LoginContent(
        modifier = modifier,
        onBackClick = navigateBack,
        onNavigateRegister = navigateToRegister,
        onNavigateHome = navigateToHome
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onNavigateRegister: () -> Unit,
    onNavigateHome: () -> Unit
) {
    val context = LocalContext.current
    val email = remember { mutableStateOf(TextFieldValue()) }
    val emailErrorState = remember { mutableStateOf(false) }
    val passwordErrorState = remember { mutableStateOf(false) }
    val password = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,

        ) {
        Text(
            text = "Log In",
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
            text = "Senang melihatmu kembali",
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.size(8.dp))
        Text(text = "Email", fontWeight = FontWeight.Bold ,fontSize = 14.sp, modifier = Modifier.padding(start = 16.dp, top = 64.dp))
        OutlinedTextField(
            value = email.value,
            onValueChange = {
                if (emailErrorState.value) {
                    emailErrorState.value = false
                }
                email.value = it
            },
            isError = emailErrorState.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 0.dp),
            label = {
                Text(text = "Enter Email*")
            },
        )
        if (emailErrorState.value) {
            Text(text = "Required", color = Color.Red)
        }
        Spacer(Modifier.size(16.dp))
        Text(text = "Password", fontWeight = FontWeight.Bold ,fontSize = 14.sp, modifier = Modifier.padding(start = 16.dp))
        val passwordVisibility = remember { mutableStateOf(true) }
        OutlinedTextField(
            value = password.value,
            onValueChange = {
                if (passwordErrorState.value) {
                    passwordErrorState.value = false
                }
                password.value = it
            },
            isError = passwordErrorState.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 0.dp),
            label = {
                Text(text = "Enter Password*")
            },
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
        Button(
            onClick = {
                when {
                    email.value.text.isEmpty() -> {
                        emailErrorState.value = true
                    }
                    password.value.text.isEmpty() -> {
                        passwordErrorState.value = true
                    }
                    else -> {
                        passwordErrorState.value = false
                        emailErrorState.value = false
                        Toast.makeText(
                            context,
                            "Logged in successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        onNavigateHome()
                    }
                }
            },
            content = {
                Text(text = "Login", color = Color.White)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 0.dp),
            colors = ButtonDefaults.buttonColors(Color.Blue)
        )
        Spacer(Modifier.size(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Belum punya akun? ",
                color = Color.Black)

            Text(
                modifier = Modifier
                    .clickable {
                        onNavigateRegister()
                    },
                text = " Daftar Akun",
                color = Color.Blue,
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navigateBack = {},
        navigateToRegister = {},
        navigateToHome = {}
    )
}