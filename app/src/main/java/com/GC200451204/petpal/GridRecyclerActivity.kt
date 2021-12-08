package com.GC200451204.petpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.petpal.R
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


        //Add button is not required now.
//        binding.addActionButton.setOnClickListener {
//            startActivity(Intent(this, AddPetDetailsActivity::class.java))
//        }

        setSupportActionBar(binding.mainToolBar.toolbar)
    }


//     Adding the main_menu to the toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

   // This method connects an action with the icon selected from the menu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            //When clicked on add button in toolbar
            R.id.action_add ->{
                startActivity(Intent(applicationContext, AddPetDetailsActivity::class.java))
                return true
            }
            R.id.action_list ->{
                startActivity(Intent(applicationContext, GridRecyclerActivity::class.java))
                return true
            }
            R.id.action_user_profile -> {
                startActivity(Intent(applicationContext, UserProfileActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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