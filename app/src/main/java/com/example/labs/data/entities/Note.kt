package com.example.labs.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize()
@Entity(tableName = "notes")
class Note(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "note") val note: String,
    @ColumnInfo(name = "description") val description: String,
) : Parcelable {
    @IgnoredOnParcel
    @ColumnInfo(name = "onCreatedAt")
    val onCreatedAt: LocalDateTime = LocalDateTime.now()

    @IgnoredOnParcel
    @ColumnInfo(name = "onUpdatedAt")
    val onUpdatedAt: LocalDateTime = LocalDateTime.now()

    @IgnoredOnParcel
    @ColumnInfo(name = "onDeletedAt")
    val onDeleteAt: LocalDateTime? = null
}