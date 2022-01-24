package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.cats.Cat

class CatAdapter(private val cat:List<Cat>, private val catSelected:(Cat)->Unit)
    : RecyclerView.Adapter<CatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout
                    .layouy_cat_item, parent, false),
            catSelected
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setCat(cat[position])
    }

    override fun getItemCount(): Int = cat.size

    class ViewHolder(private val view: View, private val catSelected: (Cat) -> Unit):RecyclerView.ViewHolder(view){
        private var image: ImageView = view.findViewById<ImageView>(R.id.imageCat)

        fun setCat(cat:Cat){

            Glide.with(view.context)
                .load(cat.url.last())
                .into(image)
        }

    }
}