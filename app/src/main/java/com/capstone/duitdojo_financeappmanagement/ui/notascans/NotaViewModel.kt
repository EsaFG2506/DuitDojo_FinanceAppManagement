package com.capstone.duitdojo_financeappmanagement.ui.notascans

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.duitdojo_financeappmanagement.repository.PhotoRepository
import kotlinx.coroutines.launch
import java.io.File

class NotaViewModel(private val photoRepository: PhotoRepository) : ViewModel() {

    fun uploadPhoto(imageFile: File) = photoRepository.uploadPhoto(imageFile)

    companion object {
        private const val TAG = "NotaViewModel"
    }
}