package com.merp.jet.weather.forecast.app.screens.main

import androidx.lifecycle.ViewModel
import com.merp.jet.weather.forecast.app.repository.WeatherRepository
import com.merp.jet.weather.forecast.app.data.DataOrException
import com.merp.jet.weather.forecast.app.model.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    suspend fun getWeatherData(city: String, units: String): DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(city, units)
    }
}