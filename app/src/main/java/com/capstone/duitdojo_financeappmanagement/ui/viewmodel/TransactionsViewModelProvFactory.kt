package com.capstone.duitdojo_financeappmanagement.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.duitdojo_financeappmanagement.repository.TransactionsRepository

class TransactionsViewModelProvFactory(val repository: TransactionsRepository, val app: Application):
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TransactionsViewModel(repository,app) as T
    }
}