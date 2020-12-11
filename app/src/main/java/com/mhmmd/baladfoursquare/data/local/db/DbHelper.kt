package com.mhmmd.baladfoursquare.data.local.db

import com.mhmmd.baladfoursquare.data.model.VenueDetails
import io.reactivex.Completable
import io.reactivex.Single

interface DbHelper {
    fun saveVenueInDb(venue: VenueDetails) : Completable
    fun saveVenueListInDb(venueList: List<VenueDetails>) : Completable
    fun loadVenuesListFromDb() : Single<List<VenueDetails>>
}