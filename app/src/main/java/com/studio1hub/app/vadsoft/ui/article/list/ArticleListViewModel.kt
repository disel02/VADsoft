package com.studio1hub.app.vadsoft.ui.article.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studio1hub.app.vadsoft.data.repository.ArticleRepository
import com.studio1hub.app.vadsoft.model.Article
import com.studio1hub.app.vadsoft.utils.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ArticleListViewModel(private val articleRepository: ArticleRepository): ViewModel(){

    private val _postLiveData = MutableLiveData<State<List<Article>>>()

    val postLivaData: LiveData<State<List<Article>>>
        get() = _postLiveData

    fun getPostsModel(){
        viewModelScope.launch {
            articleRepository.getArticleRepo().collect {
                _postLiveData.value = it
            }
        }
    }
}
