package com.example.petpal
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.petpal.databinding.ActivityRecyclerListBinding

class RecyclerListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRecyclerListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerListBinding.inflate(layoutInflater)
        setContentView(binding.root)

    //get data from the view model
        val viewModel : PetListViewModel by viewModels()
        viewModel.getPets().observe( this, { pets ->
            var recyclerViewAdapter = RecyclerViewAdapter(this, pets)
            binding.verticalRecyclerView.adapter = recyclerViewAdapter
        })
    }
}