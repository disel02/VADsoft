package com.studio1hub.app.vadsoft.ui.user.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import coil.transform.CircleCropTransformation
import com.studio1hub.app.vadsoft.R
import com.studio1hub.app.vadsoft.data.local.article.ArticlePostDatabase
import com.studio1hub.app.vadsoft.data.local.user.UserPostDatabase
import com.studio1hub.app.vadsoft.data.remote.ArticleServiceBuilder
import com.studio1hub.app.vadsoft.ui.article.detail.ArticleDetailViewModel
import com.studio1hub.app.vadsoft.ui.base.ArticleViewModelFactory
import com.studio1hub.app.vadsoft.ui.base.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_user_detail.*
import kotlinx.android.synthetic.main.user_item.view.*

class UserDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: UserDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        supportActionBar?.title = getString(R.string.appbar_user_detail)

        val postId: Int = intent.getIntExtra("user_id", 0)

        setupViewModel()
        initPost(postId)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            UserViewModelFactory(
                UserPostDatabase.getInstance(this).getUserDao(),
                ArticleServiceBuilder.provideRetrofitService()
            )
        ).get(UserDetailViewModel::class.java)
    }

    private fun initPost(articleId: Int) {
        viewModel.getUser(articleId).observe(this, Observer { user ->
            ivuserprofile.load(user.avatar) {
                placeholder(R.drawable.ic_photo)
                transformations(CircleCropTransformation())
            }
            tvusername.text = user.name
            tvuserdesignation.text = user.designation
            tvusercity.text = user.city
            tvuserabout.text = user.about
        })
    }
}