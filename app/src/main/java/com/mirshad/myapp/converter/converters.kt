package com.mirshad.myapp.converter

import androidx.room.TypeConverter
import java.util.Date

object Converters {
    @TypeConverter
    @JvmStatic
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    @JvmStatic
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}
