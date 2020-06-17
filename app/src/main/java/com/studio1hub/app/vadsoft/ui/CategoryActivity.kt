package com.studio1hub.app.vadsoft.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.studio1hub.app.vadsoft.R
import com.studio1hub.app.vadsoft.ui.article.list.ArticleListActivity
import com.studio1hub.app.vadsoft.ui.user.list.UserListActivity
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        supportActionBar?.title = getString(R.string.appbar_category)

        btnarticle.setOnClickListener {
            startActivity(Intent(this, ArticleListActivity::class.java))
        }

        btnuser.setOnClickListener {
            startActivity(Intent(this, UserListActivity::class.java))
        }
    }
}