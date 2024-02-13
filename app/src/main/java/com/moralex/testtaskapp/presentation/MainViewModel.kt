package com.moralex.testtaskapp.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moralex.testtaskapp.data.model.NumberFactEntity
import com.moralex.testtaskapp.domain.useCases.GetAllNumberFactsUseCase
import com.moralex.testtaskapp.domain.useCases.GetNumberDescribingUseCase
import com.moralex.testtaskapp.domain.useCases.GetNumberFactByIdUseCase
import com.moralex.testtaskapp.domain.useCases.GetNumberFactByTextUseCase
import com.moralex.testtaskapp.domain.useCases.GetRandomNumberDescribingUseCase
import com.moralex.testtaskapp.domain.useCases.SaveNumberFactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNumberDescribingUseCase: GetNumberDescribingUseCase,
    private val getRandomNumberDescribingUseCase: GetRandomNumberDescribingUseCase,
    private val getNumberFactByIdUseCase: GetNumberFactByIdUseCase,
    private val getNumberFactByTextUseCase: GetNumberFactByTextUseCase,
    private val getAllNumberFactsUseCase: GetAllNumberFactsUseCase,
    private val insertNumberFactUseCase: SaveNumberFactUseCase
) : ViewModel() {

    private val _data = MutableStateFlow<String?>(null)
    val data: StateFlow<String?> = _data

    private val _allNumberFacts = MutableStateFlow<List<NumberFactEntity>>(emptyList())
    val allNumberFacts: StateFlow<List<NumberFactEntity>> = _allNumberFacts
    val factFlow = MutableStateFlow<NumberFactEntity?>(null)


    fun fetchData(number: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = if (number != null) {
                getNumberDescribingUseCase.getDescribing(number)
            } else {
                getRandomNumberDescribingUseCase.getDescribingForRandomNumber()
            }
            result.collect {
                _data.value = it.getOrThrow().string()
            }
        }
    }

    fun getAllNumberFacts() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllNumberFactsUseCase.execute().collect { facts ->
                _allNumberFacts.value = facts
            }
        }
    }

    fun getNumberFactById(id: Long): StateFlow<NumberFactEntity?> {

        viewModelScope.launch(Dispatchers.IO ){
            getNumberFactByIdUseCase.execute(id).collect { fact ->
                factFlow.value = fact
            }
        }
        return factFlow
    }

    fun insertNumberFact(numberFact: NumberFactEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingFact = getNumberFactByTextUseCase.execute(numberFact.text).firstOrNull()
            if (existingFact == null) {
                insertNumberFactUseCase.execute(numberFact)
            }
        }
    }
}