package com.example.petpal


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter (val context : Context,
                           val pets : List<PetDetails>)   : RecyclerView.Adapter<RecyclerViewAdapter.PetViewHolder>(){

    /**
     * This class is used to allow us to access the item_pet.xml objects
     */
    inner class PetViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
    }

    /**
     * This connects (aka inflates) the individual ViewHolder (which is the link to the item_pet.xml)
     * with the RecyclerView
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_pet, parent, false)
        return PetViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: PetViewHolder, position: Int) {
        val pet = pets[position]

        viewHolder.nameTextView.text = pet.petName
    }

    override fun getItemCount(): Int {
        return pets.size
    }


}