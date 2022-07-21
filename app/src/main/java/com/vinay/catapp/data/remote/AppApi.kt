package com.vinay.catapp.data.remote

import com.vinay.catapp.BuildConfig
import com.vinay.catapp.domain.model.CatBreedModel
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApi {
//    @GET("v1/breeds?limit=10&page=0")
    @GET("v1/breeds")
    suspend fun getListings(
        @Query("limit") limit: Int,@Query("page") page: Int): ArrayList<CatBreedModel>

    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
    }
}