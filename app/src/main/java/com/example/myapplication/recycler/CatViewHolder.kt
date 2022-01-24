package com.example.myapplication.recycler

import androidx.lifecycle.*
import com.example.myapplication.cats.Cat
import com.example.myapplication.service.CatService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatViewHolder:ViewModel(), LifecycleEventObserver {

    private var catsService: CatService?=null

     val catLiveData= MutableLiveData<List<Cat>>()

    fun setCatService(service: CatService){
        this.catsService=service
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                println("ON_CREATE")
                getCat()
            }

        }
    }

    private fun getCat() {
        viewModelScope.launch(Dispatchers.IO) {
            val cats = catsService?.getCat()?.breeds?: listOf()
            catLiveData.postValue((cats?: listOf()) as List<Cat>?)
        }
    }

}
