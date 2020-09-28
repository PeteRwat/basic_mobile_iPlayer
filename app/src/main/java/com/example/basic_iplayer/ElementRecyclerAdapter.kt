package com.example.basic_iplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class ElementRecyclerAdapter(private val elementData: Array<Element>, val elementClickListener: OnElementClickListener) :
    RecyclerView.Adapter<ElementRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val cardImage: ImageView = view.findViewById<ImageView>(R.id.eleImage)
        val cardMasterBrand: TextView = view.findViewById<TextView>(R.id.eleMasterBrand)
        val cardTitle: TextView = view.findViewById<TextView>(R.id.eleTitle)
        val cardDetails: TextView = view.findViewById<TextView>(R.id.eleDetails)

        fun bind(element: Element, clickListener: OnElementClickListener)
        {
            itemView.setOnClickListener {
                clickListener.onElementClicked(element)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ElementRecyclerAdapter.MyViewHolder {
        val exampleItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.element_card, parent, false)

        return MyViewHolder(exampleItemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imageURL: String = elementData[position].images.standard.replace("{recipe}", "160x90")

        Picasso.get().load(imageURL).into(holder.cardImage)

        holder.cardTitle.text = elementData[position].title
        if(elementData[position].type == "episode"){
            holder.cardMasterBrand.text = elementData[position].master_brand.titles.small
            holder.cardDetails.text = elementData[position].subtitle
        } else {
            holder.cardMasterBrand.text = "Collection"
            holder.cardDetails.text = "${elementData[position].count.toString()} episodes"
        }
        holder.bind(elementData[position], elementClickListener)
    }

    override fun getItemCount() = elementData.size
}

interface OnElementClickListener{
    fun onElementClicked(element: Element)
}

