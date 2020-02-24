package com.hefny.hady.bellmantask.di

import com.hefny.hady.bellmantask.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [MainViewModelModule::class]
    )
    abstract fun contributeAuthActivity(): MainActivity
}