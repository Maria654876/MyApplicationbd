package com.example.myapplication.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatServiceImp private constructor():CatNetwork{

    private lateinit var catService: CatService

    companion object{

        private var instance:CatServiceImp?=null

        fun getInstance():CatServiceImp{
            if (instance==null){
                instance= CatServiceImp()
            }
            return instance!!
        }
    }

    init {
        initService()
    }

    private fun initService(){
        val retrofit=Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        catService =retrofit.create(CatService::class.java)
    }

    override fun getCatService(): CatService=catService
}