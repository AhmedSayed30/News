package com.example.news.api

import com.example.news.model.TabsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("/v2/top-headlines/sources")
    fun getTabs(
        @Query("apiKey") apikey : String
    ): Call<TabsResponse>
}