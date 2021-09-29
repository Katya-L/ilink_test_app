package com.example.testapp.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.data.ImgViewModel

class ListFragment : Fragment() {

    private lateinit var mImgViewModel: ImgViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mImgViewModel = ViewModelProvider(this).get(ImgViewModel::class.java)
        mImgViewModel.readAllData.observe(viewLifecycleOwner, { img ->
            adapter.setData(img)
        })
        return view
    }
}