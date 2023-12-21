package com.capstone.duitdojo_financeappmanagement.ui.news

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.duitdojo_financeappmanagement.repository.NewsRepository

class NewsViewModelFactory(val app: Application, val newsRepository: NewsRepository): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app, newsRepository) as T
    }
}