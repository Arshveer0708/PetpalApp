package com.example.petpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.petpal.databinding.ActivityGridRecyclerBinding

class GridRecyclerActivity : AppCompatActivity(), GridAdapter.PetItemListener {
    private lateinit var binding : ActivityGridRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get data from the view model
        val viewModel: PetListViewModel by viewModels()
        viewModel.getPets().observe(this, { pets ->
            var gridAdapter = GridAdapter(this, pets, this)
            binding.gridRecyclerView.adapter = gridAdapter
        })

        binding.addActionButton.setOnClickListener {
            startActivity(Intent(this, AddPetDetailsActivity::class.java))
        }
    }

    /**
     * When a pet is selected, pass the PetDetails information to the comment activity
     */
    override fun petSelected(pet: PetDetails) {
        val intent = Intent(this, PetInformationActivity::class.java)
        intent.putExtra("id", pet.id)
        intent.putExtra("petName", pet.petName)
        intent.putExtra("petAge", pet.petAge)
        intent.putExtra("petCategory", pet.petCategory)
        intent.putExtra("address", pet.address)
        intent.putExtra("petBreed", pet.petBreed)
        intent.putExtra("contact", pet.contact)
        intent.putExtra("petQualities", pet.petQualities)
        startActivity(intent)


    }

}