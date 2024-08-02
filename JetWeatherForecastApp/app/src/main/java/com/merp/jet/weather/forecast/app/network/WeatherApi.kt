package com.merp.jet.weather.forecast.app.network

import com.merp.jet.weather.forecast.app.model.WeatherItem
import com.merp.jet.weather.forecast.app.model.WeatherObject
import com.merp.jet.weather.forecast.app.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("appid") appid: String = Constants.API_KEY,
        @Query("units") units: String = "imperial"
    ): WeatherObject
}