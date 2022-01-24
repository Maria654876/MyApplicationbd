package com.example.myapplication.service

import com.example.myapplication.cats.Cat
import retrofit2.http.*

interface CatService {

    companion object{
        private const val API_KEY="2b8ee743-3cb8-45fe-9e46-7bb699129abf"
        private const val BASE_PATH="v1"
        private const val FORMAT ="json"

    }

    @GET("$BASE_PATH/{status}/{search}")
   suspend fun getCat(
        @Path("status") status: String ="images",
        @Path("search") search: String="search",
        @Query("apiKey") apiKey: String=API_KEY,
        @Query("format") format:String=FORMAT,
        ):Cat


//    @POST("$BASE_PATH/{status}/{search}")
//    fun saveTopCat(
//        @Path("topCat")topCat:String="topCat",
//        @Body cat: Cat
//
//    )
    //https://api.thecatapi.com/v1/images/search?api_key=018835e4-57b1-47c7-abe3-68d5a34369f8&format=json
}