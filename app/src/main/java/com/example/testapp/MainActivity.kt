package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.TableRow
import com.bumptech.glide.Glide
import com.example.testapp.Interface.CatApiService
import com.example.testapp.Interface.DuckApiService
import com.example.testapp.Models.CatModel
import com.example.testapp.Models.DuckModel
import com.example.testapp.Retrofit.RetrofitCatClient
import com.example.testapp.Retrofit.RetrofitDuckClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image_view)
    }

    fun showCatClick(view: android.view.View) {
        val tableRow2: TableRow = findViewById(R.id.row2)
        tableRow2.gravity = Gravity.BOTTOM

        val call = RetrofitCatClient.getClient("https://thatcopy.pw/catapi/")
            .create(CatApiService::class.java).getImage()
        call.enqueue(object : Callback<CatModel> {
            override fun onFailure(call: Call<CatModel>, t: Throwable) {}
            override fun onResponse(call: Call<CatModel>, response: Response<CatModel>) {
                val webpurl = response.body()!!.webpurl
                Glide.with(view).load(webpurl).override(1000, 1000)
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
                    .into(imageView)
            }
        })
    }

    fun showDuckClick(view: android.view.View) {
        val tableRow2: TableRow = findViewById(R.id.row2)
        tableRow2.gravity = Gravity.BOTTOM

        val call = RetrofitDuckClient.getClient("https://random-d.uk/api/")
            .create(DuckApiService::class.java).getImage()
        call.enqueue(object : Callback<DuckModel> {
            override fun onFailure(call: Call<DuckModel>, t: Throwable) {}
            override fun onResponse(call: Call<DuckModel>, response: Response<DuckModel>) {
                val url = response.body()!!.url
                Glide.with(view).load(url).override(1000, 1000)
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
                    .into(imageView)
            }
        })
    }
}

