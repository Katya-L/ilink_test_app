package com.example.testapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_img_table")
data class Model (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var url: String
)
