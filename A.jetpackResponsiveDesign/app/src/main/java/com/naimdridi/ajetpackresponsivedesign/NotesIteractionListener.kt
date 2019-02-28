package com.naimdridi.ajetpackresponsivedesign

interface NotesIteractionListener {
    fun editNotaClick(note: Note)
    fun deleteNoteClick(note: Note)
    fun favoriteNoteClick(note: Note)
}