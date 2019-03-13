package com.naimdridi.minitwitter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class RequestLogin {

    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("password")
    @Expose
    var password: String? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param email
     * @param password
     */
    constructor(email: String, password: String) : super() {
        this.email = email
        this.password = password
    }

}