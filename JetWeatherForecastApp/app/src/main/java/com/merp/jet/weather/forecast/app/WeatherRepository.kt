package com.merp.jet.weather.forecast.app

import com.merp.jet.weather.forecast.app.data.DataOrException
import com.merp.jet.weather.forecast.app.model.WeatherObject
import com.merp.jet.weather.forecast.app.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(cityQuery: String): DataOrException<WeatherObject, Boolean, Exception> {
        val response = try {
            api.getWeather(cityQuery)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}