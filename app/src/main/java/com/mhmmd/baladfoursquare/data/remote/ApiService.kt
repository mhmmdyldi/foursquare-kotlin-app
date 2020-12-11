package com.mhmmd.baladfoursquare.data.remote

import com.mhmmd.baladfoursquare.data.model.ExploredVenuesDetails
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v2/venues/explore")
    fun exploreVenuesApiCall(@Query("client_id") clientId: String,
    @Query("client_secret") clientSecret: String,
    @Query("ll") cordinate: String, @Query("query") category: String,
    @Query("v") version: Int, @Query("limit") limit: Int): Observable<ExploredVenuesDetails>
}