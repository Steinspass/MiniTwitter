package com.naimdridi.ajetpackresponsivedesign.db

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.naimdridi.ajetpackresponsivedesign.db.dao.NotesDao
import com.naimdridi.ajetpackresponsivedesign.db.entity.EntityNote

class NoteRepository(app: Application){

    private val noteDao: NotesDao
    private val listNote: LiveData<List<EntityNote>>

    init {
        val notesRoomDB = NoteRoomDatabase.getDatabase(app)
        noteDao = notesRoomDB.notesDao()
        listNote = noteDao.getAll()
    }

    // El fragment que necesita recibir la nueva lista de datos
    fun getAllNotes(): LiveData<List<EntityNote>> {
        return listNote
    }

    // El fragment que inserte una nueva nota, deberá comunicarlo a este ViewModel
     fun insertNote(newEntityNotes: EntityNote) {
        insertAsyncTask(noteDao).execute(newEntityNotes)

    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: NotesDao) : AsyncTask<EntityNote, Void, Void>(){
        override fun doInBackground(vararg params: EntityNote): Void? {
          mAsyncTaskDao.insert(params[0])
            return null
        }

    }


    fun updateNote(EntityNotes: EntityNote) {
        updateAsyncTask(noteDao).execute(EntityNotes)

    }


    private class updateAsyncTask internal constructor(private val mAsyncTaskDao: NotesDao) : AsyncTask<EntityNote, Void, Void>(){
        override fun doInBackground(vararg params: EntityNote): Void? {
            mAsyncTaskDao.update(params[0])
            return null
        }

    }


}