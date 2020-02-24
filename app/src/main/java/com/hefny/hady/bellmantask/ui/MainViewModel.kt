package com.hefny.hady.bellmantask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hefny.hady.bellmantask.Repository
import com.hefny.hady.bellmantask.models.HomeResponse
import com.hefny.hady.bellmantask.util.DataState
import javax.inject.Inject

class MainViewModel
@Inject
constructor(
    private val repository: Repository
) : ViewModel() {
    val homeResponseLiveData: LiveData<DataState<HomeResponse>> = repository.getHomeApi()
}