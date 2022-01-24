package com.example.myapplication.fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.recycler.CatAdapter
import com.example.myapplication.recycler.CatViewHolder
import com.example.myapplication.service.CatNetwork
import com.example.myapplication.service.CatServiceImp

class CatFragment: Fragment() {

    private val viewModel by viewModels<CatViewHolder>()
    private lateinit var recyclerCat: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        val fadeBrowser = inflater.inflateTransition(R.transition.fade_browser)
        val slideBrowser = inflater.inflateTransition(R.transition.slide_browser)

        enterTransition = slideBrowser
        exitTransition = fadeBrowser
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val catNetwork = CatServiceImp.getInstance() as CatNetwork
        viewModel.setCatService(catNetwork.getCatService())
        lifecycle.addObserver(viewModel)

//        recyclerCat=view.findViewById(R.id.recyclerView)
//        recyclerCat.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        setCats()
        subscribeToLiveData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    private fun setCats() {

    }

    private fun subscribeToLiveData() {
        viewModel.catLiveData.observe(viewLifecycleOwner) { cats ->
            recyclerCat.adapter = CatAdapter(cats) { cat ->
                println(cat)
            }
        }
    }

}