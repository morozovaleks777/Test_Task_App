package com.moralex.testtaskapp.presentation

import android.util.Log
import androidx.compose.material.ripple.R
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moralex.testtaskapp.data.model.NumberFact
import com.moralex.testtaskapp.domain.useCases.GetNumberDescribingUseCase
import com.moralex.testtaskapp.domain.useCases.GetRandomNumberDescribingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNumberDescribingUseCase: GetNumberDescribingUseCase,
    private val getRandomNumberDescribingUseCase: GetRandomNumberDescribingUseCase
): ViewModel() {
    private val _data = MutableStateFlow<String?>("")
    val data = _data as StateFlow<String?>
    fun fetchData(number: String?){
       viewModelScope.launch(Dispatchers.IO) {
           if(number != null){
           getNumberDescribingUseCase.getDescribing(number).collect{
               _data.value = it.getOrThrow().string()
           }
           } else {
              getRandomNumberDescribingUseCase.getDescribingForRandomNumber().collect{
                  _data.value = it.getOrThrow().string()
              }
           }
           Log.d("checkData", "fetchData: $data")
       }
    }
    init {
        fetchData(null)
    }
}