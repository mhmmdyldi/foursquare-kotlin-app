package com.mhmmd.baladfoursquare.data.remote

import com.mhmmd.baladfoursquare.data.model.ExploredVenuesDetails
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiHelperImp @Inject constructor(val appApiService: ApiService): ApiHelper {

    override fun exploreVenuesApiCall(clientId: String, clientSecret: String,
        cordinate: String, category: String,
        version: Int, limit: Int): Observable<ExploredVenuesDetails> {
            return appApiService.exploreVenuesApiCall(clientId, clientSecret, cordinate, category, version, limit)
    }
}