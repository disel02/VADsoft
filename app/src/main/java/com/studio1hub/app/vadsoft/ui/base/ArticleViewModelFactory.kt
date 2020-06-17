package com.studio1hub.app.vadsoft.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.studio1hub.app.vadsoft.data.local.article.ArticleDao
import com.studio1hub.app.vadsoft.data.remote.AppService
import com.studio1hub.app.vadsoft.data.repository.ArticleRepository
import com.studio1hub.app.vadsoft.data.repository.UserRepository
import com.studio1hub.app.vadsoft.model.User
import com.studio1hub.app.vadsoft.ui.article.detail.ArticleDetailViewModel
import com.studio1hub.app.vadsoft.ui.article.list.ArticleListViewModel
import com.studio1hub.app.vadsoft.ui.user.list.UserListViewModel
import java.lang.IllegalArgumentException

class ArticleViewModelFactory (private val articleDao: ArticleDao, private val appService: AppService): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleListViewModel::class.java))
        {
            return ArticleListViewModel(
                ArticleRepository(articleDao, appService)
            ) as T
        }
        else if (modelClass.isAssignableFrom(ArticleDetailViewModel::class.java))
        {
            return ArticleDetailViewModel(
                ArticleRepository(articleDao, appService)
            ) as T
        }
        throw IllegalArgumentException("Unknown Class Name")
    }

}
