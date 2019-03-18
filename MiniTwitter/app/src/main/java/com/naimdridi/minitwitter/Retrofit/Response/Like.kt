package com.naimdridi.minitwitter.Retrofit.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Like {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("username")
    @Expose
    var username: String? = null
    @SerializedName("descripcion")
    @Expose
    var descripcion: String? = null
    @SerializedName("website")
    @Expose
    var website: String? = null
    @SerializedName("photoUrl")
    @Expose
    var photoUrl: String? = null
    @SerializedName("created")
    @Expose
    var created: String? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param id
     * @param username
     * @param created
     * @param website
     * @param descripcion
     * @param photoUrl
     */
    constructor(
        id: Int?,
        username: String,
        descripcion: String,
        website: String,
        photoUrl: String,
        created: String
    ) : super() {
        this.id = id
        this.username = username
        this.descripcion = descripcion
        this.website = website
        this.photoUrl = photoUrl
        this.created = created
    }

}