package com.hanung.bullience.ui.screen.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hanung.bullience.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }

    Surface(
        color = Color(R.color.blue_main_color),
        modifier = Modifier.fillMaxSize()
    ) {
        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = {
                        OvershootInterpolator(2f).getInterpolation(it)
                    }
                )
            )
            delay(2000L)
            navController.navigate("SignInScreen")
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_bullience),
                contentDescription = "Logo",
                modifier = Modifier.scale(scale.value)
            )
        }

    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    SplashScreen(navController = rememberNavController())
}