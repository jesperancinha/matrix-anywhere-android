package nl.joaofilipesabinoesperancinha.matrixanywhere

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import nl.joaofilipesabinoesperancinha.matrixanywhere.ui.theme.MatrixAnywhereTheme


class TestCoroutineActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MatrixAnywhereTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TestCoroutineOutline(intent = intent, coroutineActivity = this)
                }
            }
        }
    }
}

@Composable
fun TestCoroutineOutline(
    intent: Intent?,
    coroutineActivity: TestCoroutineActivity
) {

    Button(
        modifier = Modifier
            .testTag(SUBMIT_BACK)
            .fillMaxWidth(0.5f),
        onClick = {
            val navigate = Intent(coroutineActivity, MainActivity::class.java)
            startActivity(coroutineActivity, navigate, null)
            coroutineActivity.finish()
        }) {
        Text(text = "Back")
    }
}


@Preview
@Composable
fun TestCoroutineOutlineDemo() {
    return TestCoroutineOutline(intent = Intent(), coroutineActivity = TestCoroutineActivity())
}