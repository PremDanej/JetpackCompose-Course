package com.merp.jet.weather.forecast.app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.merp.jet.weather.forecast.app.model.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao() : WeatherDao
}