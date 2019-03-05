package com.naimdridi.ajetpackresponsivedesign.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Notes")
data class EntityNote(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val title: String = "",
    val content: String = "",
    val favorite: Boolean,
    val color: Int
)