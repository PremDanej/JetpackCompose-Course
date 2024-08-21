package com.merp.jet.weather.forecast.app.repository

import android.util.Log
import com.merp.jet.weather.forecast.app.data.DataOrException
import com.merp.jet.weather.forecast.app.model.Weather
import com.merp.jet.weather.forecast.app.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(
        cityQuery: String,
        units: String
    ): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(cityQuery, units)
        } catch (e: Exception) {
            Log.d("ERR", "getWeather: ERR -> ${e.message}")
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}