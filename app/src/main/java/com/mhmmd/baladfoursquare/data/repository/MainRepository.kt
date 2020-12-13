package com.mhmmd.baladfoursquare.data.repository

import com.mhmmd.baladfoursquare.data.local.db.DbHelper
import com.mhmmd.baladfoursquare.data.local.prefs.PreferencesHelper
import com.mhmmd.baladfoursquare.data.model.ExploredVenuesDetails
import com.mhmmd.baladfoursquare.data.remote.ApiHelper
import io.reactivex.Observable

interface MainRepository: ApiHelper, DbHelper, PreferencesHelper {
    fun getNearestVenuesApiCall(category: String,
                      version: Int, limit: Int): Observable<ExploredVenuesDetails.ExploredVenuesResponse.ExploredVenuesGroups>
}