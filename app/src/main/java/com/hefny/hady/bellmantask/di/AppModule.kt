package com.hefny.hady.bellmantask.di

import android.app.Application
import com.hefny.hady.bellmantask.adapters.AttractionsListAdapter
import com.hefny.hady.bellmantask.adapters.HotSpotsListAdapter
import com.hefny.hady.bellmantask.api.HomeApi
import com.hefny.hady.bellmantask.repository.Repository
import com.hefny.hady.bellmantask.util.ConnectivityUtil
import com.hefny.hady.bellmantask.util.Constants
import com.hefny.hady.bellmantask.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideHomeAPi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideConnectivityUtil(application: Application): ConnectivityUtil {
        return ConnectivityUtil(application)
    }

    @Singleton
    @Provides
    fun provideRepository(homeApi: HomeApi, connectivityUtil: ConnectivityUtil): Repository {
        return Repository(homeApi, connectivityUtil)
    }

    @Provides
    fun provideAttractionsAdapter(): AttractionsListAdapter {
        return AttractionsListAdapter()
    }

    @Provides
    fun provideHotSpotsAdapter(): HotSpotsListAdapter {
        return HotSpotsListAdapter()
    }
}