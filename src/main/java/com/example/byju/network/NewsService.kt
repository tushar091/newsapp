package com.example.byju.network

import com.example.byju.model.HeadLines
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    fun getArticles(@Query("country") countryCode: String,
                    @Query("apiKey") apiKey: String): Observable<HeadLines>


}