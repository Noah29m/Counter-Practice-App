package com.example.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.counterapp.ui.counter.CounterView
import com.example.counterapp.ui.counter.CustomCircularProgressIndicator
import com.example.counterapp.ui.theme.CounterAppTheme
import com.example.counterapp.ui.theme.navygreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CustomCircularProgressIndicator(
                        initialValue = 50,
                        primaryColor = navygreen,
                        secondaryColor = Color.Black,
                        circleRadius = 230f
                    ) {

                    }
                    CounterView()

                }

            }
        }
    }
}

