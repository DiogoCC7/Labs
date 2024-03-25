package com.example.labs.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.labs.data.converters.LocalDateTimeConverter
import com.example.labs.data.daos.NoteDao
import com.example.labs.data.entities.Note

@Database(entities = [Note :: class], version = 1, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class)
abstract class NoteDatabase : RoomDatabase(){
    abstract  fun  noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
