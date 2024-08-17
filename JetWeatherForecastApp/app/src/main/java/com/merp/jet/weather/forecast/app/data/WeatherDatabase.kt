package com.merp.jet.weather.forecast.app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.merp.jet.weather.forecast.app.model.Favorite
import com.merp.jet.weather.forecast.app.model.Unit

@Database(entities = [Favorite::class, Unit::class], version = 2 , exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}