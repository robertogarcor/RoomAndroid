package com.example.rgc.roomandroid.data.domain.model

import java.io.Serializable

data class Movie(
                 val id: Int,
                 val title:String,
                 val type:String,
                 val duration: String,
                 val description: String,
                 val enclosure: String) : Serializable
