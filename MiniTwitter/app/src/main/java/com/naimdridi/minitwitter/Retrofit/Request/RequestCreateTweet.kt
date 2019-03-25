package com.naimdridi.minitwitter.Retrofit.Request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class RequestCreateTweet {

    @SerializedName("mensaje")
    @Expose
    var mensaje: String? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param mensaje
     */
    constructor(mensaje: String) : super() {
        this.mensaje = mensaje
    }

}