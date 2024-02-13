package com.moralex.testtaskapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moralex.testtaskapp.ui.theme.TestTaskAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTaskAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NumbersFactDisplay()
                }
            }
        }
    }
}

@Composable
fun NumbersFactDisplay(viewModel: MainViewModel = hiltViewModel()) {
    val data = viewModel.data.collectAsState()

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        data.value?.let {
            it.let { it1 ->
                Text(
                    text = it1,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}