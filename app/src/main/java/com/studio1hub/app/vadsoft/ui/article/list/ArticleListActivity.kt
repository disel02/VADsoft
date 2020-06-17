package com.studio1hub.app.vadsoft.ui.article.list

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
import com.studio1hub.app.vadsoft.data.local.article.ArticlePostDatabase
import com.studio1hub.app.vadsoft.data.remote.ArticleServiceBuilder
import com.studio1hub.app.vadsoft.model.Article
import com.studio1hub.app.vadsoft.ui.article.list.adapter.ArticleRecyclerViewAdapter
import com.studio1hub.app.vadsoft.ui.base.ArticleViewModelFactory
import com.studio1hub.app.vadsoft.utils.State
import kotlinx.android.synthetic.main.activity_article_list.*

class ArticleListActivity : AppCompatActivity() {

    private val TAG = ArticleListActivity::class.java.simpleName

    private lateinit var listViewModel: ArticleListViewModel
    private lateinit var adapterArticle: ArticleRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)
        supportActionBar?.title = getString(R.string.appbar_article_list)

        setupViewModel()
        setupUI()
        initPosts()

    }

    private fun setupViewModel() {
        listViewModel = ViewModelProviders.of(this,
            ArticleViewModelFactory(ArticlePostDatabase.getInstance(this).getArticleDao(), ArticleServiceBuilder.provideRetrofitService())
        ).get(ArticleListViewModel::class.java)
    }

    private fun setupUI() {
        rvarticles.layoutManager = LinearLayoutManager(this)
        adapterArticle = ArticleRecyclerViewAdapter(arrayListOf())
        rvarticles.addItemDecoration(
            DividerItemDecoration(
                rvarticles.context,
                (rvarticles.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvarticles.adapter = adapterArticle
    }

    private fun initPosts() {
        listViewModel.postLivaData.observe(
            this, Observer { data ->
                when(data){
                    is State.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        rvarticles.visibility = View.GONE
                    }
                    is State.Success -> {
                        if (data.data.isNotEmpty())
                        {
                            retrieveList(data.data.toMutableList())
                            rvarticles.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                        }
                        else
                            Log.d(TAG, "State success_empty")
                    }
                    is State.Error -> {
                        Log.d(TAG, "State error")
                        rvarticles.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, data.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        )

        if (listViewModel.postLivaData.value !is State.Success)
        {
            listViewModel.getPostsModel()
        }
    }

    private fun retrieveList(article: List<Article>) {
        adapterArticle.apply {
            addArticle(article)
            notifyDataSetChanged()
        }
    }


}