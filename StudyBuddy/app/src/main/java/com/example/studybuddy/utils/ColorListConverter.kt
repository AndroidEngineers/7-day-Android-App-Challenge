package com.example.studybuddy.utils

import androidx.room.TypeConverter

class ColorListConverter {

    @TypeConverter
    fun fromColorList(colorList: List<Int>): String {
        return colorList.joinToString(","){it.toString()}
    }

    @TypeConverter
    fun toColorList(colorString: String): List<Int> {
        return colorString.split(",").map { it.toInt() }
    }

}