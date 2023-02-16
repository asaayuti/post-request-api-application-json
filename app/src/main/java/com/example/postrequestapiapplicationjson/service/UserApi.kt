package com.example.postrequestapiapplicationjson.service

import com.example.postrequestapiapplicationjson.model.user.UserRequest
import com.example.postrequestapiapplicationjson.model.user.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("api/users")
    fun createUser(@Body req: UserRequest): Call<UserResponse>

}