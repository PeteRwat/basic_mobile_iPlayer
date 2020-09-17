package com.example.basic_iplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val menuData: Array<Category>, val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val cardTextView: TextView = view.findViewById<TextView>(R.id.main_menu_text)

        fun bind(title:String, clickListener: OnItemClickListener)
        {
            itemView.setOnClickListener {
                clickListener.onItemClicked(title)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapter.MyViewHolder {
        val exampleItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_menu_text, parent, false)

        return MyViewHolder(exampleItemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.cardTextView.text = menuData[position].title
        holder.bind(menuData[position].id, itemClickListener)
    }

    override fun getItemCount() = menuData.size
}

interface OnItemClickListener{
    fun onItemClicked(title:String)
}

