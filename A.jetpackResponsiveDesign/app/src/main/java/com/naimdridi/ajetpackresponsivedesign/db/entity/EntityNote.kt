package com.naimdridi.ajetpackresponsivedesign.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Notes")
data class EntityNote(

    val title: String,
    val content: String,
    val favorite: Boolean,
    val color: String
){
    @PrimaryKey(autoGenerate = true) val id: Int = 0
}