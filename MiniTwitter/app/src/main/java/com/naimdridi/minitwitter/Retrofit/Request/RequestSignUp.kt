package com.naimdridi.minitwitter.Retrofit.Request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class RequestSignUp {

    @SerializedName("username")
    @Expose
    var username: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("password")
    @Expose
    var password: String? = null
    @SerializedName("code")
    @Expose
    var code: String? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param username
     * @param email
     * @param code
     * @param password
     */
    constructor(username: String, email: String, password: String, code: String) : super() {
        this.username = username
        this.email = email
        this.password = password
        this.code = code
    }

}