package com.mhmmd.baladfoursquare.data.repository

import com.mhmmd.baladfoursquare.data.local.db.DbHelper
import com.mhmmd.baladfoursquare.data.local.prefs.PreferencesHelper
import com.mhmmd.baladfoursquare.data.model.ExploredVenuesDetails
import com.mhmmd.baladfoursquare.data.model.VenueDetails
import com.mhmmd.baladfoursquare.data.remote.ApiHelper
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImp @Inject constructor(private val mApiHelper: ApiHelper, private val mDbHelper: DbHelper,
                                            private val mPreferencesHelper: PreferencesHelper): MainRepository {

    override fun getNearestVenuesApiCall(
        clientId: String,
        clientSecret: String,
        cordinate: String,
        category: String,
        version: Int,
        limit: Int
    ): Observable<ExploredVenuesDetails.ExploredVenuesResponse.ExploredVenuesGroups> {
        return mApiHelper.exploreVenuesApiCall(clientId, clientSecret, cordinate, category, version, limit)
            .map { response -> response.exploredVenuesResponse?.groups?.get(0) }
    }

    override fun exploreVenuesApiCall(
        clientId: String, clientSecret: String,
        cordinate: String, category: String,
        version: Int, limit: Int): Observable<ExploredVenuesDetails> {
            return mApiHelper.exploreVenuesApiCall(clientId, clientSecret, cordinate, category, version, limit)

    }

    override fun saveVenueInDb(venue: VenueDetails): Completable {
        return mDbHelper.saveVenueInDb(venue)
    }

    override fun saveVenueListInDb(venueList: List<VenueDetails>): Completable {
        return mDbHelper.saveVenueListInDb(venueList)
    }

    override fun loadVenuesListFromDb(): Single<List<VenueDetails>> {
        return mDbHelper.loadVenuesListFromDb()
    }

    override fun deleteVenuesFromDb(): Completable {
        return mDbHelper.deleteVenuesFromDb()
    }

    override fun setCurrentLocationsLongitudeInPrefs(currentLongitude: Double) {
        mPreferencesHelper.setCurrentLocationsLongitudeInPrefs(currentLongitude)
    }

    override fun getCurrentLocationsLongitudeFromPrefs(): Double {
        return mPreferencesHelper.getCurrentLocationsLongitudeFromPrefs()
    }

    override fun setCurrentLocationsLatitudeInPrefs(currentLatitude: Double) {
        mPreferencesHelper.setCurrentLocationsLatitudeInPrefs(currentLatitude)
    }

    override fun getCurrentLocationsLatitudeFromPrefs(): Double {
        return mPreferencesHelper.getCurrentLocationsLatitudeFromPrefs()
    }
}