package com.studio1hub.app.vadsoft.ui.article.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.studio1hub.app.vadsoft.data.repository.ArticleRepository

class ArticleDetailViewModel (private val articleRepository: ArticleRepository) : ViewModel() {

    fun getArticle(id: Int) = articleRepository.getPostById(id).asLiveData()
}