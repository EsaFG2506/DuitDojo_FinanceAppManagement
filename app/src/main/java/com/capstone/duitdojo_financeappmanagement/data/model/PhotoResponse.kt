package com.capstone.duitdojo_financeappmanagement.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PhotoResponse(

	@field:SerializedName("menu")
	val menu: List<MenuItem> = emptyList(),

	@field:SerializedName("status")
	val status: Status
)

@Entity(tableName = "nota")
@Parcelize
data class MenuItem(

	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	val id: Int? = null,

	@ColumnInfo(name = "category")
	@field:SerializedName("category")
	val category: String,

	@ColumnInfo(name = "nm")
	@field:SerializedName("nm")
	val nm: String
) : Parcelable

data class Status(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("message")
	val message: String
)
