package com.hefny.hady.bellmantask.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hefny.hady.bellmantask.R
import com.hefny.hady.bellmantask.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    val TAG = "AppDebug"
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, providerFactory).get(MainViewModel::class.java)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.homeResponseLiveData.observe(this, Observer { dataState ->
            dataState?.let {
                dataState.data?.getContentIfNotHandled()?.let {
                    Log.d(TAG, "subscribeObservers: data: ${it.data}")
                }
                dataState.error?.getContentIfNotHandled()?.let { message ->
                    Log.d(TAG, "subscribeObservers: error: $message")
                }
                Log.d(TAG, "subscribeObservers: loading: ${dataState.loading}")
            }
        })
    }
}