package com.example.petpal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.example.petpal.databinding.ActivityPetListBinding
import com.google.firebase.firestore.FirebaseFirestore

class PetListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPetListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //create an instance of our PetListViewModel
        val viewModel : PetListViewModel by viewModels()

        viewModel.getPets().observe( this, { pets ->
            binding.linearLayout.removeAllViews()

            for (pet in pets) {
                //Add pet to the LinearList
                val textView = TextView(this)
                textView.text = pet.petName
                textView.textSize = 20f
                binding.linearLayout.addView(textView)
            }
        })

    }
}