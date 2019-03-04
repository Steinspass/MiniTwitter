package com.naimdridi.ajetpackresponsivedesign

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import android.arch.lifecycle.LiveData




@Dao
interface NoteDao {

    @Insert
    fun insert(note: EntityNote)

    @Update
    fun update(note: EntityNote)

    @Query("DELETE FROM notes")
    fun deleteAll()

    @Query("DELETE FROM notes WHERE id = :idNote")
    fun deleteById(idNote: Int)

    @Query("SELECT * FROM notes ORDER BY title ASC")
    fun getAll(): LiveData<List<EntityNote>>

    @Query("SELECT * FROM notes WHERE favorite LIKE 1")
    fun getAllFavorites(): LiveData<List<EntityNote>>
}