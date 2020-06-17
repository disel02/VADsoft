package com.studio1hub.app.vadsoft.ui.article.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.studio1hub.app.vadsoft.R
import com.studio1hub.app.vadsoft.data.local.article.ArticlePostDatabase
import com.studio1hub.app.vadsoft.data.remote.ArticleServiceBuilder
import com.studio1hub.app.vadsoft.ui.base.ArticleViewModelFactory
import com.studio1hub.app.vadsoft.utils.NumFormat
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: ArticleDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        supportActionBar?.title = getString(R.string.appbar_article_detail)

        val postId: Int = intent.getIntExtra("article_id",0)

        setupViewModel()
        initPost(postId)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this,
            ArticleViewModelFactory(ArticlePostDatabase.getInstance(this).getArticleDao(), ArticleServiceBuilder.provideRetrofitService())
        ).get(ArticleDetailViewModel::class.java)
    }

    private fun initPost(articleId: Int) {
        viewModel.getArticle(articleId).observe(this, Observer { article ->
            ivarticleimg.load(article.media.image)
            tvarticlecontent.text = article.content
            tvarticletitle.text = article.media.title
            tvarticleurl.text = article.media.url
            tvarticlelikes.text = "${NumFormat.numConvert(article.likes.toDouble(), 0)} Likes"
            tvarticlecomments.text = "${NumFormat.numConvert(article.comments.toDouble(), 0)} Comments"

            tvarticleurl.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(article.media.url)))
            }
        })
    }
}