package com.studio1hub.app.vadsoft.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.studio1hub.app.vadsoft.R
import com.studio1hub.app.vadsoft.data.local.UserPostDatabase
import com.studio1hub.app.vadsoft.data.remote.ServiceBuilder
import com.studio1hub.app.vadsoft.ui.base.ViewModelFactory
import com.studio1hub.app.vadsoft.utils.State

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        initPosts()

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this,
            ViewModelFactory(UserPostDatabase.getInstance(this).getUserDao(), ServiceBuilder.provideRetrofitService())
        ).get(MainViewModel::class.java)
    }

    private fun initPosts() {
        viewModel.postLivaData.observe(
            this, Observer { data ->
                when(data){
                    is State.Loading -> {
                        Log.d(TAG,"loading..")
                    }
                    is State.Success -> {
                        if (data.data.isNotEmpty())
                        {
                            Log.d(TAG,"list: ${data.data.toMutableList()}")
                        }
                        else
                        {
                            Log.d(TAG,"empty")
                        }
                    }
                    is State.Error -> {
                        Log.d(TAG,"error: ${data.message}")
                    }
                }
            }
        )

        if (viewModel.postLivaData.value !is State.Success)
        {
            viewModel.getPostsModel()
        }
    }


}