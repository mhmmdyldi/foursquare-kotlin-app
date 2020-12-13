package com.mhmmd.baladfoursquare.ui.nearestLocations.viewModel

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mhmmd.baladfoursquare.data.model.VenueDetails
import com.mhmmd.baladfoursquare.data.repository.MainRepository
import com.mhmmd.baladfoursquare.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ExploredLocationsViewModel(
    private val mainRepository: MainRepository,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val mCompositeDisposable = CompositeDisposable()
    private val observableNearestVenues = MutableLiveData<List<VenueDetails>>()
    var venuesList = ArrayList<VenueDetails>()

    private fun loadNearestLocationsFromDB() {
        mCompositeDisposable.add(mainRepository.loadVenuesListFromDb()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe { loadedVenues ->
                venuesList.clear()
                venuesList.addAll(loadedVenues)
                observableNearestVenues.postValue(venuesList)
            }
        )
    }

    private fun fetchNearestLocationsFromApi() {
        mCompositeDisposable.add(mainRepository.getNearestVenuesApiCall("coffee",
            20201208, 5
        )
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe { exploredVenues ->
                venuesList.clear()
                for (item in exploredVenues.venuesItems!!)
                    item.venue?.let { venuesList.add(it) }
                deleteNearestVenuesFromLocalDb()
            })
    }

    private fun deleteNearestVenuesFromLocalDb() {
        mCompositeDisposable.add(mainRepository.deleteVenuesFromDb()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe { ->
                saveNearestLocationsInDB()
            })
    }

    private fun saveNearestLocationsInDB() {
        mCompositeDisposable.add(mainRepository.saveVenueListInDb(venuesList)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe { ->
                loadNearestLocationsFromDB()
            })
    }

    fun userLocationChanged(newLocation: Location) {
        var currentLocationLat = mainRepository.getCurrentLocationsLatitudeFromPrefs()
        var currentLocationLong = mainRepository.getCurrentLocationsLongitudeFromPrefs()
        var newLocationLat = newLocation.latitude
        var newLocationLong = newLocation.longitude
        Log.d("Test", "updateCurrentLocationDetails: $currentLocationLat, $currentLocationLong")
        if (isLocationHasNotBeenSetBefore(currentLocationLat, currentLocationLong)) {
            updateCurrentLocationDetails(newLocationLat, newLocationLong)
            fetchNearestLocationsFromApi()
        } else if (isUserDisplacedMoreThanHundredMeters(currentLocationLat, currentLocationLong,
                newLocationLat, newLocationLong)) {
            updateCurrentLocationDetails(newLocationLat, newLocationLong)
            fetchNearestLocationsFromApi()
        }else {
            if (venuesList.size == 0)
                loadNearestLocationsFromDB()
        }
    }

    private fun isLocationHasNotBeenSetBefore(
        currentLocationLat: Double,
        currentLocationLong: Double
    ): Boolean {
        return currentLocationLat == 0.0 || currentLocationLong == 0.0
    }

    private fun isUserDisplacedMoreThanHundredMeters(startPointLat: Double, startPointLong: Double,
                                                     endPointLat: Double, endPointLong: Double): Boolean {
        val results = FloatArray(1)
        android.location.Location.distanceBetween(
            startPointLat,
            startPointLong,
            endPointLat,
            endPointLong,
            results
        )
        return results[0] > 100
    }

    private fun updateCurrentLocationDetails(newLocationLat: Double, newLocationLong: Double) {
        mainRepository.setCurrentLocationsLatitudeInPrefs(newLocationLat)
        mainRepository.setCurrentLocationsLongitudeInPrefs(newLocationLong)
    }

    fun getObservableNearestVenues(): MutableLiveData<List<VenueDetails>> {
        return observableNearestVenues
    }

}