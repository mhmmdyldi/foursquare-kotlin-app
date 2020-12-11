package com.mhmmd.baladfoursquare.ui.nearestLocations.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mhmmd.baladfoursquare.data.model.VenueDetails
import com.mhmmd.baladfoursquare.data.repository.MainRepository
import com.mhmmd.baladfoursquare.utils.AppConstants
import com.mhmmd.baladfoursquare.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ExploredLocationsViewModel(private val mainRepository: MainRepository, private val schedulerProvider: SchedulerProvider): ViewModel() {

    private val mCompositeDisposable = CompositeDisposable()
    init {
    }

    private fun loadNearestLocationsFromDB() {
        mCompositeDisposable.add(mainRepository.loadVenuesListFromDb()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe {venuesList ->
                Log.d("Test", "loadNearestLocationsFromDB: ")
            }
        )
    }

    private fun fetchNearestLocationsFromApi() {
        mCompositeDisposable.add(mainRepository.getNearestVenuesApiCall(AppConstants.CLIENT_ID,
        AppConstants.CLIENT_SECRET, "35.7739,51.3436", "coffee",
        20201208, 5)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe { exploredVenues ->
                var venuesList = ArrayList<VenueDetails>()
                for (item in exploredVenues.venuesItems!!)
                    item.venue?.let { venuesList.add(it) }
                saveNearestLocationsInDB(venuesList)
            })
    }

    private fun saveNearestLocationsInDB(venuesList: ArrayList<VenueDetails>) {
        mCompositeDisposable.add(mainRepository.saveVenueListInDb(venuesList)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe { ->
                Log.d("Test", "saveNearestLocationsInDB: ")
            })
    }


}