package com.mhmmd.baladfoursquare.data.local.prefs

import android.location.Location

interface PreferencesHelper {
    fun setCurrentLocationsLongitudeInPrefs(currentLongitude: Double)
    fun getCurrentLocationsLongitudeFromPrefs(): Double
    fun setCurrentLocationsLatitudeInPrefs(currentLatitude: Double)
    fun getCurrentLocationsLatitudeFromPrefs(): Double
}