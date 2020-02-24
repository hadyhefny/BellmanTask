package com.hefny.hady.bellmantask.di

import androidx.lifecycle.ViewModelProvider
import com.hefny.hady.bellmantask.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}