package com.moralex.testtaskapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.moralex.testtaskapp.R
import com.moralex.testtaskapp.data.model.NumberFactEntity
import com.moralex.testtaskapp.presentation.MainViewModel
import com.moralex.testtaskapp.presentation.navigation.AppScreens


@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel = hiltViewModel()) {
    val data = viewModel.data.collectAsState()

    val list = viewModel.allNumberFacts.collectAsState()
    val numberInput = remember { mutableStateOf("") }
    LaunchedEffect(data.value) {
        data.value?.let { NumberFactEntity(text = it) }?.let { viewModel.insertNumberFact(it) }
        viewModel.getAllNumberFacts()
    }


    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.onPrimary) {
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = numberInput.value,
                onValueChange = { newValue ->
                        numberInput.value = newValue
                },
                label = { Text(stringResource(R.string.enter_a_number)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                Button(
                    onClick = {
                        viewModel.fetchData(numberInput.value)
                        numberInput.value = ""
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp)
                ) {
                    Text(stringResource(R.string.enter_number))
                }

                Button(
                    onClick = {
                        viewModel.fetchData(null)
                        numberInput.value = ""
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp)
                ) {
                    Text(stringResource(R.string.random_number))
                }
            }

            LazyColumn {
                item {
                    Text(
                        text = data.value ?: "",
                        modifier = Modifier.padding(vertical = 16.dp),
                        style = MaterialTheme.typography.headlineSmall.copy(MaterialTheme.colorScheme.primary)
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.history),
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .fillMaxSize(),
                        style = MaterialTheme.typography.headlineLarge.copy(
                            MaterialTheme.colorScheme.primary, textAlign = TextAlign.Center
                        )
                    )
                }
                items(list.value) { fact ->

                    Text(
                        text = fact.text,
                        modifier = Modifier
                            .padding(4.dp)
                            .clickable {
                                navController.navigate(
                                    "${AppScreens.DetailScreen.name}?text=${fact.text}"
                                )
                            },
                        style = MaterialTheme.typography.bodyLarge.copy(MaterialTheme.colorScheme.primary),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
