package com.mhmmd.baladfoursquare.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        initializeViewModel()
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initializeUiComponent()
    }

    abstract fun initializeViewModel()

    private fun initializeUiComponent() {

    }

}