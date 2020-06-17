package com.studio1hub.app.vadsoft.ui.user.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.studio1hub.app.vadsoft.R
import com.studio1hub.app.vadsoft.data.local.user.UserPostDatabase
import com.studio1hub.app.vadsoft.data.remote.UserServiceBuilder
import com.studio1hub.app.vadsoft.model.User
import com.studio1hub.app.vadsoft.ui.base.UserViewModelFactory
import com.studio1hub.app.vadsoft.ui.user.list.adapter.UserRecyclerViewAdapter
import com.studio1hub.app.vadsoft.utils.State
import kotlinx.android.synthetic.main.activity_user_list.*

class UserListActivity : AppCompatActivity() {

    private val TAG = UserListActivity::class.java.simpleName

    private lateinit var viewModel: UserListViewModel
    private lateinit var adapterArticle: UserRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        supportActionBar?.title = getString(R.string.appbar_user_list)

        setupViewModel()
        setupUI()
        initPosts()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this,
            UserViewModelFactory(UserPostDatabase.getInstance(this).getUserDao(), UserServiceBuilder.provideRetrofitService())
        ).get(UserListViewModel::class.java)
    }

    private fun setupUI() {
        rvusers.layoutManager = LinearLayoutManager(this)
        adapterArticle = UserRecyclerViewAdapter(arrayListOf())
        rvusers.addItemDecoration(
            DividerItemDecoration(
                rvusers.context,
                (rvusers.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvusers.adapter = adapterArticle
    }

    private fun initPosts() {
        viewModel.postLivaData.observe(
            this, Observer { data ->
                when(data){
                    is State.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        rvusers.visibility = View.GONE
                    }
                    is State.Success -> {
                        if (data.data.isNotEmpty())
                        {
                            retrieveList(data.data.toMutableList())
                            rvusers.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                        }
                        else
                            Log.d(TAG, "State success_empty")
                    }
                    is State.Error -> {
                        Log.d(TAG, "State error")
                        rvusers.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, data.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        )

        if (viewModel.postLivaData.value !is State.Success)
        {
            viewModel.getPostsModel()
        }
    }

    private fun retrieveList(users: List<User>) {
        adapterArticle.apply {
            addUser(users)
            notifyDataSetChanged()
        }
    }
}