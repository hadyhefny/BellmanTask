package com.hefny.hady.bellmantask.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hefny.hady.bellmantask.R
import com.hefny.hady.bellmantask.adapters.AttractionsListAdapter
import com.hefny.hady.bellmantask.adapters.HotSpotsListAdapter
import com.hefny.hady.bellmantask.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    val TAG = "AppDebug"
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var attractionsListAdapter: AttractionsListAdapter

    @Inject
    lateinit var hotSpotsListAdapter: HotSpotsListAdapter

    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, providerFactory).get(MainViewModel::class.java)
        initAttractionsRecyclerAdapter()
        initHotSpotsRecyclerAdapter()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.homeResponseLiveData.observe(this, Observer { dataState ->
            dataState?.let {
                // loading state
                showProgressBar(dataState.loading)
                // success state
                dataState.data?.getContentIfNotHandled()?.let { response ->
                    // attractions
                    response.data?.attractions?.let { list ->
                        list.let {
                            Log.d(TAG, "subscribeObservers: data: ${it}")
                            attractionsListAdapter.submitList(it)
                        }
                    }
                    // hot-spots
                    response.data?.hotSpots?.let { list ->
                        list.let {
                            Log.d(TAG, "subscribeObservers: data: ${it}")
                            hotSpotsListAdapter.submitList(it)
                        }
                    }
                }
                // error state
                dataState.error?.getContentIfNotHandled()?.let { message ->
                    Log.d(TAG, "subscribeObservers: error: $message")
                    showSnackBar(message)
                }
            }
        })
    }

    private fun initHotSpotsRecyclerAdapter() {
        hot_spots_recycler_view.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = hotSpotsListAdapter
        }
    }

    private fun initAttractionsRecyclerAdapter() {
        attractions_recycler_view.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = attractionsListAdapter
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(progress_bar, message, Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun showProgressBar(isLoading: Boolean) {
        if (isLoading) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }
}