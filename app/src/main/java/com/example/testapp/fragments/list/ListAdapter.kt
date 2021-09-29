package com.example.testapp.fragments.list

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapp.R
import com.example.testapp.data.Model

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var imgList = emptyList<Model>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = imgList[position]
        val imageView = holder.itemView.findViewById<ImageView>(R.id.image_view)
        Glide.with(holder.itemView).load(currentItem.url).override(500).into(imageView)
    }

    override fun getItemCount(): Int {
        return imgList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(img: List<Model>) {
        this.imgList = img
        notifyDataSetChanged()
    }
}