package com.example.news.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiManager {
    companion object{
        private var retrofit : Retrofit? = null
        val apiKey = "d345bd7c207246288e20202fcd1aab03"
        @Synchronized
        private fun getInstance():Retrofit{
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
        fun getApis(): WebServices{
            return getInstance().create(WebServices::class.java)
        }
    }
}