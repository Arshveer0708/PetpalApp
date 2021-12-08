package com.GC200451204.petpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petpal.databinding.ActivityPetInformationBinding

class PetInformationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPetInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.petNameTextView.text = intent.getStringExtra("petName")
        binding.petAgeTextView.text = intent.getStringExtra("petAge")
        binding.petCategoryTextView.text = intent.getStringExtra("petCategory")
        binding.petAddressTextView.text = intent.getStringExtra("address")
        binding.petBreedTextView.text = intent.getStringExtra("petBreed")
        binding.petContactTextView.text = intent.getStringExtra("contact")
        binding.petQualitiesTextView.text = intent.getStringExtra("petQualities")

        binding.backActionButton.setOnClickListener {
            startActivity(Intent(this, GridRecyclerActivity::class.java))
        }

//        binding.smsActionButton.setOnClickListener {
//            startActivity(Intent(this, SmsActivity::class.java))
//        }

    }
}