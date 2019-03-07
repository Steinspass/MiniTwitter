package com.naimdridi.ajetpackresponsivedesign.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.naimdridi.ajetpackresponsivedesign.db.dao.NotesDao
import com.naimdridi.ajetpackresponsivedesign.db.entity.EntityNote

@Database(entities = [EntityNote::class], version = 1, exportSchema = false)
abstract class NoteRoomDatabase : RoomDatabase(){
    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null

        fun getDatabase(context: Context): NoteRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return  tempInstance
            }
            synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteRoomDatabase::class.java,
                    "Notes_Database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}