package com.example.rgc.roomandroid.data.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(@PrimaryKey(autoGenerate = true) val id: Int,
                 val title:String,
                 val type:String,
                 val duration: String,
                 val description: String,
                 val enclosure: String) {
}