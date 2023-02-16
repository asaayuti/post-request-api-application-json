package com.example.postrequestapiapplicationjson.service

import com.example.postrequestapiapplicationjson.model.Comment
import retrofit2.Call
import retrofit2.http.GET

interface CommentApi {

    @GET("comments")
    // pakai list karena data api dari awal sudah menggunakan array
    // dengan ditandai kurung kotak pada baris paling atas data api
    fun getComments() : Call<List<Comment>>
}