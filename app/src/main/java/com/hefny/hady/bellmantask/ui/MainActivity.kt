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


class MainActivity : DaggerAppCompatActivity(), View.OnClickListener {
    val TAG = "AppDebug"
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var attractionsListAdapter: AttractionsListAdapter

    @Inject
    lateinit var hotSpotsListAdapter: HotSpotsListAdapter

    private var isFABOpen = false

    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, providerFactory).get(MainViewModel::class.java)
        initAttractionsRecyclerAdapter()
        initHotSpotsRecyclerAdapter()
        subscribeObservers()
        fab.setOnClickListener(this)
        myView.setOnClickListener(this)
        bar.setOnClickListener(this)
    }

    private fun subscribeObservers() {
        hideViews()
        viewModel.homeResponseLiveData.observe(this, Observer { dataState ->
            dataState?.let {
                // loading state
                showProgressBar(dataState.loading)
                // success state
                dataState.data?.let { response ->
                    showViews()
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

    private fun hideViews(){
        hot_spot_icon_image_view.visibility = View.GONE
        hot_spot_name.visibility = View.GONE
        view_all_text_view.visibility = View.GONE
        view_all_icon_image_view.visibility = View.GONE

        attractions_icon_image_view.visibility = View.GONE
        attractions_name.visibility = View.GONE
        attractions_view_all_text_view.visibility = View.GONE
        attractions_view_all_icon_image_view.visibility = View.GONE
    }

    private fun showViews(){
        hot_spot_icon_image_view.visibility = View.VISIBLE
        hot_spot_name.visibility = View.VISIBLE
        view_all_text_view.visibility = View.VISIBLE
        view_all_icon_image_view.visibility = View.VISIBLE

        attractions_icon_image_view.visibility = View.VISIBLE
        attractions_name.visibility = View.VISIBLE
        attractions_view_all_text_view.visibility = View.VISIBLE
        attractions_view_all_icon_image_view.visibility = View.VISIBLE
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

    override fun onClick(v: View?) {
        if (v == fab) {
            myView.visibility = View.VISIBLE
            if (!isFABOpen) {
                showFABMenu()
            } else {
                closeFABMenu()
            }
        } else if (v == myView || v == bar) {
            myView.visibility = View.GONE
            closeFABMenu()
        }
    }

    private fun showFABMenu() {
        isFABOpen = true
        hot_spots_fab.animate().translationX(-300f)
        events_fab.animate().translationY(-300f)
        events_fab.animate().translationX(-130f)
        attractions_fab.animate().translationY(-300f)
        attractions_fab.animate().translationX(130f)
        loaction_pin_fab.animate().translationX(300f)
        hot_spots_fab.animate().alpha(1f)
        events_fab.animate().alpha(1f)
        attractions_fab.animate().alpha(1f)
        loaction_pin_fab.animate().alpha(1f)
    }

    private fun closeFABMenu() {
        isFABOpen = false
        hot_spots_fab.animate().translationX(0f)
        events_fab.animate().translationY(0f)
        events_fab.animate().translationX(0f)
        attractions_fab.animate().translationY(0f)
        attractions_fab.animate().translationX(0f)
        loaction_pin_fab.animate().translationX(0f)
        hot_spots_fab.animate().alpha(0f)
        events_fab.animate().alpha(0f)
        attractions_fab.animate().alpha(0f)
        loaction_pin_fab.animate().alpha(0f)
    }
}