package com.example.testapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImgViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Model>>
    private val repository: ImgRepository

    init {
        val savedImgDao = ImgDatabase.getDatabase(application).savedImgDao()
        repository = ImgRepository(savedImgDao)
        readAllData = repository.readAllData
    }

    fun addImg(img: Model) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addImg(img)
        }
    }
}