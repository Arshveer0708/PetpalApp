package com.example.petpal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GridAdapter (val context : Context,
                   val pets : List<PetDetails>,
                   val itemListener: PetItemListener)   : RecyclerView.Adapter<GridAdapter.PetViewHolder>() {

    /**
     * This class is used to allow us to access the item_pet.xml objects
     */
    inner class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.textView)
        val nameTextAge = itemView.findViewById<TextView>(R.id.textAge)
        val nameTextCategory = itemView.findViewById<TextView>(R.id.textCategory)
        val nameTextAddress = itemView.findViewById<TextView>(R.id.textAddress)

    }

    /**
     * This connects (aka inflates) the individual ViewHolder (which is the link to the item_pet.xml)
     * with the RecyclerView
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_grid_pet, parent, false)
        return PetViewHolder(view)
    }

    /**
     * This method will bind the viewHolder with a specific pet object
     */
    override fun onBindViewHolder(viewHolder: PetViewHolder, position: Int) {
        val pet = pets[position]
        viewHolder.nameTextView.text = pet.petName
        viewHolder.nameTextAge.text = pet.petAge
        viewHolder.nameTextCategory.text = pet.petCategory
        viewHolder.nameTextAddress.text = pet.address

        viewHolder.itemView.setOnClickListener {
            itemListener.petSelected(pet)
        }
    }

    override fun getItemCount(): Int {
        return pets.size
    }

    interface PetItemListener {
        fun petSelected( pet : PetDetails)
    }
}