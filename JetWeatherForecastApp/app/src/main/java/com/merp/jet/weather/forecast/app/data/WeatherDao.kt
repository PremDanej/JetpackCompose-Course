package com.merp.jet.weather.forecast.app.data

 import androidx.room.Dao
 import androidx.room.Delete
 import androidx.room.Insert
 import androidx.room.OnConflictStrategy
 import androidx.room.Query
 import androidx.room.Update
 import com.merp.jet.weather.forecast.app.model.Favorite
 import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query(value = "SELECT * FROM fav_tbl")
    fun getFavorites(): Flow<List<Favorite>>

    @Query(value = "SELECT * FROM fav_tbl WHERE city =:city")
    suspend fun getFavById(city: String) : Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite)

    @Query(value = "DELETE FROM fav_tbl")
    suspend fun deleteAllFavorite()

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)
}