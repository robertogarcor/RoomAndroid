package com.example.rgc.roomandroid.data

import com.example.rgc.roomandroid.data.domain.model.Movie
import com.example.rgc.roomandroid.data.roomdb.Movie as RoomMovie

fun Movie.toRoomMovie(): RoomMovie =
    RoomMovie(
        id,
        title,
        type,
        duration,
        description,
        enclosure
    )

fun RoomMovie.toDomainMovie(): Movie =
    Movie(
        id,
        title,
        type,
        duration,
        description,
        enclosure
    )