package com.moralex.testtaskapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moralex.testtaskapp.data.model.NumberFactEntity
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

    val list = viewModel.allNumberFacts.collectAsState()
    val numberInput = remember { mutableStateOf("") }
    LaunchedEffect(data.value) {
      data.value?.let { NumberFactEntity(text = it) }?.let { viewModel.insertNumberFact(it) }
        viewModel.getAllNumberFacts()
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = numberInput.value,
                onValueChange = { numberInput.value = it },
                label = { Text("Enter a number") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                Button(
                    onClick = { viewModel.fetchData(numberInput.value) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp)
                ) {
                    Text("Enter Number")
                }

                Button(
                    onClick = { viewModel.fetchData(null) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp)
                ) {
                    Text(" Random Number")
                }
            }

            LazyColumn {
                item {
                    Text(
                        text = data.value ?: "",
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }

                items(list.value) { fact ->
                    Text(
                        text = fact.text,
                        modifier = Modifier.padding(4.dp).clickable {  },
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
