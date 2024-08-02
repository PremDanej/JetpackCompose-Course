package com.merp.jet.weather.forecast.app

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merp.jet.weather.forecast.app.data.DataOrException
import com.merp.jet.weather.forecast.app.model.WeatherObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    val data: MutableState<DataOrException<WeatherObject, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    init {
        loadWeather()
    }

    fun loadWeather() {
        getWeather("Moscow")
    }

    fun getWeather(city: String) {
        viewModelScope.launch {
            if (city.isEmpty()) return@launch
            data.value.loading = true
            data.value = repository.getWeather(city)

            if (data.value.data.toString().isNotEmpty()) data.value.loading = false
        }
        Log.d("GET -> ", "getWeather: ${data.value.data.toString()}")
    }
}