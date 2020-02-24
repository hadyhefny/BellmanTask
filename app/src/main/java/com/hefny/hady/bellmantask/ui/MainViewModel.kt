package com.hefny.hady.bellmantask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hefny.hady.bellmantask.Repository
import com.hefny.hady.bellmantask.models.HomeResponse
import com.hefny.hady.bellmantask.util.GenericApiResponse
import javax.inject.Inject

class MainViewModel
@Inject
constructor(
    private val repository: Repository
) : ViewModel() {
    val homeResponseLiveData: LiveData<GenericApiResponse<HomeResponse>> = Transformations
        .switchMap(repository.getHomeApi()) {
            object : LiveData<GenericApiResponse<HomeResponse>>() {
                override fun onActive() {
                    super.onActive()
                    value = it
                }
            }
        }
}