package org.jesperancinha.matrixanywhere

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jesperancinha.matrixanywhere.ui.theme.MatrixAnywhereTheme

class MatrixForm : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MatrixAnywhereTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting2("Android", intent = intent, matrixForm = this)
                }
            }
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith("finish()"))
    override fun onBackPressed() {
        finish()
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier, intent: Intent, matrixForm: MatrixForm) {
    val width = intent.getIntExtra("width", 2)
    val data = (1..(intent.getIntExtra("height", 2) * width)).map { "X" }

    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(width),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(data) { item ->
                Card(
                    modifier = Modifier.padding(4.dp),
                    backgroundColor = Color.LightGray
                ) {
                    Text(
                        text = item,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(24.dp)
                    )
                }
            }
        }
    }
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = { matrixForm.finish() }) {
            Text(text = "Back")
        }
    }

}
