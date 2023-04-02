package com.ayberk.universtyapp

import com.ayberk.universtyapp.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInstance {

    @GET("ulkeler")
    fun locaShows(@Query("page") query: String): retrofit2.Call<Ulke>

    @GET("sehirler/2")
    fun getSehir(@Query("page") query: String): retrofit2.Call<Sehir>

    @GET("vakitler/9716")
    fun getEzan(@Query("page") query: String): retrofit2.Call<ManisaEzanSaati>

    @GET("vakitler/9206")
    fun getAnkaraEzan(@Query("page") query: String): retrofit2.Call<AnkaraEzanSaati>
}