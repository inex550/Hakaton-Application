package com.example.hakaton_bastion.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Place (
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String
)