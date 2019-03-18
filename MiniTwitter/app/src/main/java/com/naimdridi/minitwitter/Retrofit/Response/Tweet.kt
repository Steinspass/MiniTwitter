package com.naimdridi.minitwitter.Retrofit.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Tweet {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("mensaje")
    @Expose
    var mensaje: String? = null
    @SerializedName("likes")
    @Expose
    var likes: List<Like> = ArrayList<Like>()
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
     * @param id
     * @param likes
     * @param mensaje
     * @param user
     */
    constructor(id: Int?, mensaje: String, likes: List<Like>, user: User) : super() {
        this.id = id
        this.mensaje = mensaje
        this.likes = likes
        this.user = user
    }

}