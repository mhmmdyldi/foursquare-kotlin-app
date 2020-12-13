package com.mhmmd.baladfoursquare.data.local.db

import com.mhmmd.baladfoursquare.data.model.VenueDetails
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class DbHelperImp @Inject constructor(val appDatabase: AppDatabase) : DbHelper {

    override fun saveVenueInDb(venue: VenueDetails): Completable {
        return appDatabase.venueDetailsDao().insert(venue)
    }

    override fun saveVenueListInDb(venueList: List<VenueDetails>): Completable {
        return appDatabase.venueDetailsDao().insertAll(venueList)
    }

    override fun loadVenuesListFromDb(): Single<List<VenueDetails>> {
        return appDatabase.venueDetailsDao().loadAll()
    }

    override fun deleteVenuesFromDb(): Completable {
        return appDatabase.venueDetailsDao().deleteAll()
    }
}