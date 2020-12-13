package com.mhmmd.baladfoursquare.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import com.mhmmd.baladfoursquare.utils.AppConstants
import javax.inject.Inject


class PreferencesHelperImp @Inject constructor(context: Context): PreferencesHelper {
    companion object{
        private val PREF_KEY_CURRENT_LOCATION_LAT = "PREF_KEY_CURRENT_LOCATION_LAT"
        private val PREF_KEY_CURRENT_LOCATION_LONG= "PREF_KEY_CURRENT_LOCATION_LONG"
    }

    private var mPrefs: SharedPreferences

    init {
        mPrefs = context.getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE)
    }

    override fun setCurrentLocationsLongitudeInPrefs(currentLongitude: Double) {
        mPrefs.edit().putLong(PREF_KEY_CURRENT_LOCATION_LONG, java.lang.Double.doubleToRawLongBits(currentLongitude)).apply()
    }

    override fun getCurrentLocationsLongitudeFromPrefs(): Double {
        return java.lang.Double.longBitsToDouble(mPrefs.getLong(PREF_KEY_CURRENT_LOCATION_LONG, java.lang.Double.doubleToRawLongBits(
            0.0
        )))
    }

    override fun setCurrentLocationsLatitudeInPrefs(currentLatitude: Double) {
        mPrefs.edit().putLong(PREF_KEY_CURRENT_LOCATION_LAT, java.lang.Double.doubleToRawLongBits(currentLatitude)).apply()
    }

    override fun getCurrentLocationsLatitudeFromPrefs(): Double {
        return java.lang.Double.longBitsToDouble(mPrefs.getLong(PREF_KEY_CURRENT_LOCATION_LAT, java.lang.Double.doubleToRawLongBits(
            0.0
        )))
    }

}