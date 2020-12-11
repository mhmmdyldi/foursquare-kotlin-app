package com.mhmmd.baladfoursquare.ui.nearestLocations.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mhmmd.baladfoursquare.R
import com.mhmmd.baladfoursquare.ui.base.BaseActivity
import com.mhmmd.baladfoursquare.ui.base.ViewModelFactory
import com.mhmmd.baladfoursquare.ui.nearestLocations.viewModel.ExploredLocationsViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var exploredLocationsViewModel: ExploredLocationsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initializeViewModel() {
        exploredLocationsViewModel = ViewModelProvider(
            this, viewModelFactory).get(ExploredLocationsViewModel::class.java)
    }
}