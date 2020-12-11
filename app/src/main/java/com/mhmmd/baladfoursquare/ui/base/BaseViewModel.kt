package com.mhmmd.baladfoursquare.ui.base

import androidx.lifecycle.ViewModel
import com.mhmmd.baladfoursquare.utils.rx.SchedulerProvider

abstract class BaseViewModel(val mSchedulerProvider: SchedulerProvider): ViewModel() {


    open fun getSchedulerProvider(): SchedulerProvider? {
        return mSchedulerProvider
    }
}