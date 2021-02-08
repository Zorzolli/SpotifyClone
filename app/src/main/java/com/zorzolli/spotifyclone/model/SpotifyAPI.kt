package com.zorzolli.spotifyclone.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SpotifyAPI {

    @GET("ar.json?alt=media&token=063b6283-4cf8-40d2-ae62-b8c7b64dbdba")
    fun ListCategories(): Call<Categories>
}

fun retrofit(): Retrofit = Retrofit.Builder()
    .baseUrl("https://firebasestorage.googleapis.com/v0/b/zorzolli-213cd.appspot.com/o/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()