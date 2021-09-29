package com.example.testapp.data

import androidx.lifecycle.LiveData

class ImgRepository(private val savedImgDao: SavedImgDao) {

    val readAllData: LiveData<List<Model>> = savedImgDao.readAllData()

    suspend fun addImg(img: Model) {
        savedImgDao.addImg(img)
    }
}