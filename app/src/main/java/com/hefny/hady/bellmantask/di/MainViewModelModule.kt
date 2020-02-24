package com.hefny.hady.bellmantask.di

import androidx.lifecycle.ViewModel
import com.hefny.hady.bellmantask.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindAuthViewModel(mainViewModel: MainViewModel): ViewModel

}