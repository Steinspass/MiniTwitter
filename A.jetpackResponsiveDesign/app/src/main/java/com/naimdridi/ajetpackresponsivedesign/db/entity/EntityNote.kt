package com.naimdridi.ajetpackresponsivedesign.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Notes")
data class EntityNote(

    var title: String ="",
    var content: String ="",
    var favorite: Boolean = false,
    var color: String =""
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}