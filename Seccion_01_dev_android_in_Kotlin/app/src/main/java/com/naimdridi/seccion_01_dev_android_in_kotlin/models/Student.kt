package com.naimdridi.seccion_01_dev_android_in_kotlin.models

import android.os.Parcel
import android.os.Parcelable

// Parcel es un contenedor para un mensaje
// Puede ser usado para mandar un objecto serializado a traves de Intent
// Mejor rendimiento que Serializer.
data class Student(var name: String, var lastName: String, var age: Int, var isDeveloper: Boolean = true) : Parcelable {

    // Leer en el mismo orden que declaramos las propiedades
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        // tenemos que escribir los valores en el mismo orden en el que leemos en el constructor
        parcel.writeString(name)
        parcel.writeString(lastName)
        parcel.writeInt(age)
        parcel.writeByte(if(isDeveloper) 1 else 0)
    }

    // Describe el contenido del objecto Parcelable
    // 0 siempre se usa, el otro valor es 1 o CONTENTS_FILE_DESCRIPTOR, para tipos de objectos que implementan un File Descriptor
    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Student> {
        // Esto creara el objecto desde el parcel llamando al constructor Secundario
        override fun createFromParcel(source: Parcel) = Student(source)

        // Esto ayudara a serializar arrays de objectos del mismo tipo que esta clase (Student)
        override fun newArray(size: Int) = arrayOfNulls<Student>(size)

    }


}