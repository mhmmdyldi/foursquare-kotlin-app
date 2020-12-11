package com.mhmmd.baladfoursquare.data.remote

import com.mhmmd.baladfoursquare.data.model.ExploredVenuesDetails
import io.reactivex.Observable

interface ApiHelper {
    fun exploreVenuesApiCall(clientId: String, clientSecret: String,
                      cordinate: String, category: String,
                      version: Int, limit: Int): Observable<ExploredVenuesDetails>
}