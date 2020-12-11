package com.mhmmd.baladfoursquare.di.module

import android.app.Application
import android.content.Context
import com.mhmmd.baladfoursquare.data.local.db.AppDatabase
import com.mhmmd.baladfoursquare.data.local.db.DbHelper
import com.mhmmd.baladfoursquare.data.local.db.DbHelperImp
import com.mhmmd.baladfoursquare.data.remote.ApiHelper
import com.mhmmd.baladfoursquare.data.remote.ApiHelperImp
import com.mhmmd.baladfoursquare.data.remote.ApiService
import com.mhmmd.baladfoursquare.data.repository.MainRepository
import com.mhmmd.baladfoursquare.data.repository.MainRepositoryImp
import com.mhmmd.baladfoursquare.utils.rx.AppSchedulerProvider
import com.mhmmd.baladfoursquare.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApiHelper(appApiHelper: ApiHelperImp): ApiHelper {
        return appApiHelper
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideFourSquareApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.foursquare.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideScheduleProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @Singleton
    fun provideRepository(mainRepositoryImp: MainRepositoryImp): MainRepository {
        return mainRepositoryImp
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideDbHelper(appDbHelper: DbHelperImp): DbHelper {
        return appDbHelper
    }

}