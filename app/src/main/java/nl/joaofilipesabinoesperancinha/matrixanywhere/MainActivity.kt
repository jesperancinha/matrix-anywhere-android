package nl.joaofilipesabinoesperancinha.matrixanywhere

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import nl.joaofilipesabinoesperancinha.matrixanywhere.ui.theme.MatrixAnywhereTheme


const val WIDTH_TAG = "width-tag"
const val HEIGHT_TAG = "height-tag"
const val SUBMIT_MATRIX_TAG = "submit-matrix-tag"

val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    println("Caught an exception: ${throwable.message}")
}

class MainActivity : ComponentActivity() {
    private val mainScope = MainScope() + exceptionHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var text by remember { mutableStateOf("<NOT USED>") }
            var text2 by remember { mutableStateOf("<NOT USED>") }
            MatrixAnywhereTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController, this, text, text2)
            }
            LaunchedEffect(Unit) {
                mainScope.launch {
                    throw RuntimeException()
                    text = "Use height on the top"
                }

                mainScope.launch {
                    text2 = "Use width at the bottom"
                }
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }
}

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    mainActivity: MainActivity,
    text: String,
    text2: String
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            MatrixSplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MainMenu("Android", mainActivity = mainActivity, text = text, text2 = text2)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(
    name: String,
    modifier: Modifier = Modifier,
    mainActivity: MainActivity,
    text: String,
    text2: String = "<NOTHING>"
) {
    var dim by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Please let us know the size of your matrix")
        Row {
            Text(text = text)
        }
        Row {
            Text(text = text2)
        }
        Row {
            Text(
                text = "Height:",
                modifier = Modifier.width(200.dp)
            )
            TextField(
                modifier = Modifier
                    .width(50.dp)
                    .testTag(HEIGHT_TAG),
                value = dim,
                onValueChange = { newText -> if (newText.isDigitsOnly()) dim = newText },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Row {
            Text(
                text = "Width:",
                modifier = Modifier.width(200.dp)
            )
            TextField(
                modifier = Modifier
                    .width(50.dp)
                    .testTag(WIDTH_TAG),
                value = dim,
                onValueChange = { newText -> if (newText.isDigitsOnly()) dim = newText },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Button(
            onClick = {
                if (dim.isDigitsOnly() && dim.isNotEmpty()) {
                    val navigate = Intent(mainActivity, MatrixForm::class.java)
                    navigate.putExtra("height", dim.toInt())
                    navigate.putExtra("width", dim.toInt())
                    startActivity(mainActivity, navigate, null)
                }
            },
            modifier = Modifier
                .testTag(SUBMIT_MATRIX_TAG)
        ) {
            Text(text = "Submit")
        }
    }
}

@Preview
@Composable
fun MainMenuDemo() {
    MainMenu("Android", mainActivity = MainActivity(), text = "DEMO")
}