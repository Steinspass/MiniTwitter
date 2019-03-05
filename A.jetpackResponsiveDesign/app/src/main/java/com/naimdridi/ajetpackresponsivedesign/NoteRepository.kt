package com.naimdridi.ajetpackresponsivedesign

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.naimdridi.ajetpackresponsivedesign.db.dao.NotesDao
import com.naimdridi.ajetpackresponsivedesign.db.entity.EntityNote

class NoteRepository(private val notesDao: NotesDao){

    val allNotes: LiveData<List<EntityNote>> = notesDao.getAll()

    val allFavNotes: LiveData<List<EntityNote>> = notesDao.getAllFavorites()

    @WorkerThread
    suspend fun insert(note: EntityNote){
        notesDao.insert(note)
    }

    @WorkerThread
    suspend fun update(note: EntityNote){
        notesDao.update(note)
    }
}