package com.example.labs.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
@Entity(tableName = "notes")
class Note(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "note") val note: String,
    @ColumnInfo(name = "untilDate") val utilDate: LocalDateTime,
    @ColumnInfo(name = "createdAt") val createdAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = "updatedAt") var updatedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = "deletedAt") var deletedAt: LocalDateTime? = null
) : Parcelable {

    init {
        require(note.length >= MINIMUM_NOTE_LENGTH);
    }

    companion object {
        const val MINIMUM_NOTE_LENGTH = 5
    }
}