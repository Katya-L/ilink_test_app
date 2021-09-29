package com.example.testapp.fragments.add

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TableRow
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.testapp.data.ImgViewModel
import com.example.testapp.data.Model
import com.example.testapp.Interface.CatApiService
import com.example.testapp.Interface.DuckApiService
import com.example.testapp.Models.CatModel
import com.example.testapp.Models.DuckModel
import com.example.testapp.R
import com.example.testapp.R.drawable.*
import com.example.testapp.Retrofit.RetrofitCatClient
import com.example.testapp.Retrofit.RetrofitDuckClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFragment : Fragment() {

    private lateinit var mImgViewModel: ImgViewModel
    private lateinit var imageView: ImageView
    private var url: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_add, container, false)
        mImgViewModel = ViewModelProvider(this).get(ImgViewModel::class.java)
        imageView = view.findViewById(R.id.image_view)

        val savedBtn: FloatingActionButton = view.findViewById(R.id.saved_btn)
        savedBtn.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_listFragment2)
        }

        val likeBtn: FloatingActionButton = view.findViewById(R.id.like_btn)
        likeBtn.setOnClickListener {
            insertDataToDatabase()
            likeBtn.setImageResource(R.drawable.ic_liked)
        }

        val catBtn: Button = view.findViewById(R.id.show_cat_btn)
        catBtn.setOnClickListener {
            showCatClick(view)
        }

        val duckBtn: Button = view.findViewById(R.id.show_duck_btn)
        duckBtn.setOnClickListener {
            showDuckClick(view)
        }
        return view
    }

    private fun showCatClick(view: View) {
        val tableRow2: TableRow = view.findViewById(R.id.row2)
        tableRow2.gravity = Gravity.BOTTOM
        val likeBtn: View = view.findViewById(R.id.like_btn)
        likeBtn.visibility = View.VISIBLE

        val call = RetrofitCatClient.getClient("https://thatcopy.pw/catapi/")
            .create(CatApiService::class.java).getImage()
        call.enqueue(object : Callback<CatModel> {
            override fun onFailure(call: Call<CatModel>, t: Throwable) {}
            override fun onResponse(call: Call<CatModel>, response: Response<CatModel>) {
                url = response.body()!!.webpurl
                Glide.with(view).load(url).override(1000)
                    //.placeholder(R.drawable.ic_loading)
                    .error(ic_error)
                    .into(imageView)
            }
        })
    }

    private fun showDuckClick(view: View) {
        val tableRow2: TableRow = view.findViewById(R.id.row2)
        tableRow2.gravity = Gravity.BOTTOM
        val likeBtn: View = view.findViewById(R.id.like_btn)
        likeBtn.visibility = View.VISIBLE

        val call = RetrofitDuckClient.getClient("https://random-d.uk/api/")
            .create(DuckApiService::class.java).getImage()
        call.enqueue(object : Callback<DuckModel> {
            override fun onFailure(call: Call<DuckModel>, t: Throwable) {}
            override fun onResponse(call: Call<DuckModel>, response: Response<DuckModel>) {
                url = response.body()!!.url
                Glide.with(view).load(url).override(1000)
                    //.placeholder(R.drawable.ic_loading)
                    .error(ic_error)
                    .into(imageView)
            }
        })
    }

    private fun insertDataToDatabase() {
        val savedImg = Model(0, url)
        mImgViewModel.addImg(savedImg)
        Toast.makeText(requireContext(), "Сохранено", Toast.LENGTH_LONG).show()
    }
}