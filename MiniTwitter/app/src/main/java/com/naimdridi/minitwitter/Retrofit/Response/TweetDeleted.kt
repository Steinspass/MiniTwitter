package com.naimdridi.minitwitter.Retrofit.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class TweetDeleted {

    @SerializedName("mensaje")
    @Expose
    var mensaje: String? = null
    @SerializedName("user")
    @Expose
    var user: User? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param mensaje
     * @param user
     */
    constructor(mensaje: String, user: User) : super() {
        this.mensaje = mensaje
        this.user = user
    }

}