package com.example.hakaton_bastion.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.hakaton_bastion.models.database.Place

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place")
    fun getAll(): List<Place>

    @Insert
    fun addPlace(place: Place)
}