package com.capstone.duitdojo_financeappmanagement.data.room.converter

import androidx.room.TypeConverter
import com.capstone.duitdojo_financeappmanagement.data.model.Source
import com.capstone.duitdojo_financeappmanagement.utils.TransactionCategory

class Converters {

    @TypeConverter
    fun fromTransactionCategory(category: TransactionCategory):String {
        return "${category.icon},${category.description}"
    }

    @TypeConverter
    fun toTransactionCategory(value:String): TransactionCategory {
        val list = value.split(",")
        val description = list[0].toInt()
        val icon = list[1].toInt()
        return TransactionCategory(icon,description)
    }

    @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source{
        return Source(name, name)
    }

}