package com.example.hakaton_bastion.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hakaton_bastion.models.database.Place

@Database(entities = [Place::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun placeDao(): PlaceDao
}