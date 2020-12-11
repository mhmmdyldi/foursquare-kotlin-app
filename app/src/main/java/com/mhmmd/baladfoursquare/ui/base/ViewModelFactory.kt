package com.mhmmd.baladfoursquare.ui.base

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mhmmd.baladfoursquare.data.repository.MainRepository
import com.mhmmd.baladfoursquare.ui.nearestLocations.viewModel.ExploredLocationsViewModel
import com.mhmmd.baladfoursquare.utils.rx.SchedulerProvider
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val mainRepository: MainRepository, private val schedulerProvider: SchedulerProvider) :
    ViewModelProvider.NewInstanceFactory() {
    @NonNull
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExploredLocationsViewModel :: class.java)){
            return ExploredLocationsViewModel(mainRepository, schedulerProvider) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}