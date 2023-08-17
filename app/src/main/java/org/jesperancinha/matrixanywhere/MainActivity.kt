package org.jesperancinha.matrixanywhere

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jesperancinha.matrixanywhere.ui.theme.MatrixAnywhereTheme


const val WIDTH_TAG = "width-tag"
const val HEIGHT_TAG = "height-tag"
const val SUBMIT_MATRIX_TAG = "submit-matrix-tag"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MatrixAnywhereTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController, this)
            }
        }
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController, mainActivity: MainActivity) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Greeting("Android", mainActivity = mainActivity)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, mainActivity: MainActivity) {
    var dim by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Please let us know the size of your matrix")
        Row {
            Text(text = "Height:")
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(HEIGHT_TAG),
                value = dim,
                onValueChange = { newText ->
                    dim = newText
                }
            )
        }
        Row {
            Text(text = "Width:")
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(WIDTH_TAG),
                value = dim,
                onValueChange = { newText ->
                    dim = newText
                }
            )
        }
        Button(
            onClick = {
                val navigate = Intent(mainActivity, MatrixForm::class.java)
                navigate.putExtra("height", dim.toInt())
                navigate.putExtra("width", dim.toInt())
                startActivity(mainActivity, navigate, null)
            },
            modifier = Modifier
                .testTag(SUBMIT_MATRIX_TAG)
        ) {
            Text(text = "Submit")
        }
    }
}
