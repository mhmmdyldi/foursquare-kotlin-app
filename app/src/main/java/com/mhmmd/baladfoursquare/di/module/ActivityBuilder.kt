package com.mhmmd.baladfoursquare.di.module

import com.mhmmd.baladfoursquare.ui.nearestLocations.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract fun bindMainActivity() : MainActivity
}