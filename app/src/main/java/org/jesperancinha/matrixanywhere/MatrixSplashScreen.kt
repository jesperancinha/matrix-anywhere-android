package org.jesperancinha.matrixanywhere

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import org.jesperancinha.matrixanywhere.ui.theme.DarkColors
import org.jesperancinha.matrixanywhere.ui.theme.LightColors
import org.jesperancinha.R


sealed class Screen(val route: String) {
    object Splash:Screen("splash_screen")
    object Home: Screen("home_screen")
}
@Composable
fun MatrixSplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    val floatState = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        ), label = ""
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(1000)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }
    Splash(alpha = floatState.value)
}

@Composable
fun Splash(alpha: Float) {
    val colorScheme = when {
        isSystemInDarkTheme() -> DarkColors
        else -> LightColors
    }
    Box(
        modifier = Modifier
            .background(colorScheme.primary)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
            Image(
                painter = painterResource(id = R.drawable.matrixandroid),
                contentDescription = "opening"
            )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    Splash(alpha = 1f)
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun SplashScreenDarkPreview() {
    Splash(alpha = 1f)
}