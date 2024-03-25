package com.example.labs.data.converters

import androidx.room.TypeConverter
import java.time.LocalDateTime

class LocalDateTimeConverter {

    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): String? {
        return date?.toString()
    }
}