package com.mhmmd.baladfoursquare.di.component

import android.app.Application
import com.mhmmd.baladfoursquare.BaladFourSquareApp
import com.mhmmd.baladfoursquare.di.module.ActivityBuilder
import com.mhmmd.baladfoursquare.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ActivityBuilder::class, AndroidInjectionModule::class])
interface AppComponent {

    fun inject(app :BaladFourSquareApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder
        fun build() : AppComponent
    }
}