package com.naimdridi.ajetpackresponsivedesign.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.naimdridi.ajetpackresponsivedesign.db.NoteRepository
import com.naimdridi.ajetpackresponsivedesign.db.entity.EntityNote

class NewNoteViewModel(app: Application) : AndroidViewModel(app) {


    internal val allNotes: LiveData<List<EntityNote>>
    private val noteRepository: NoteRepository

    init {
        noteRepository = NoteRepository(app)
        allNotes = noteRepository.getAllNotes()
    }

    fun insert(newNotes: EntityNote){
        noteRepository.insertNote(newNotes)
    }

    fun update(notes: EntityNote){
        noteRepository.updateNote(notes)
    }




}
