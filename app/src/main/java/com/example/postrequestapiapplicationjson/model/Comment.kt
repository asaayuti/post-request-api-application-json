package com.example.postrequestapiapplicationjson.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Comment {
    // field SerializedName penulisannya harus sama dengan data api
    // atau kalau tidak pakai Serialized name nama variable nya harus disamakan dengan data api
    // kalau nama variable sudah sama dengan data api, bisa tidak pakai SerializedName
    @SerializedName("postId")
    @Expose
    var postIid: Int? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("body")
    @Expose
    var body: String? = null
}