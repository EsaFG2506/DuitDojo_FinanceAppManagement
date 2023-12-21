package com.capstone.duitdojo_financeappmanagement.ui.notascans

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.duitdojo_financeappmanagement.di.Injection
import com.capstone.duitdojo_financeappmanagement.repository.PhotoRepository

class NotaViewModelFactory(private val photoRepository: PhotoRepository)
    : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(NotaViewModel::class.java) -> {
                NotaViewModel(photoRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: NotaViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): NotaViewModelFactory {
            if (INSTANCE == null) {
                synchronized(NotaViewModelFactory::class.java) {
                    INSTANCE = NotaViewModelFactory(Injection.providePhotoRepository(context))
                }
            }
            return INSTANCE as NotaViewModelFactory
        }
    }
}