package com.GC200451204.petpal

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.petpal.R
import com.example.petpal.databinding.ActivityUserProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.userProfileChangeRequest

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUserProfileBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Make the add user name text field and button invisible if the user already exists
        binding.nameEditText.visibility = View.GONE
        binding.buttonAddName.visibility = View.GONE

        //ensure we have an authenticated user
        if (auth.currentUser == null)
            logout()
        else{
            auth.currentUser?.let{ user->
                if(user.displayName.toString().isEmpty())
                {
                    binding.nameEditText.visibility = View.VISIBLE
                    binding.buttonAddName.visibility = View.VISIBLE
                }
                else {
                    binding.textViewUserName.text = user.displayName
                }

                binding.textViewEmailID.text = user.email

                binding.userProfileDetails.text = "User Details"
            }
        }

        //Logout the user when logout button is pressed
        binding.logout.setOnClickListener {
            logout()
        }

        binding.buttonAddName.setOnClickListener {
            var userName = binding.nameEditText.text.toString()
            if (userName.isNotEmpty()){
                updateName(userName)
            }
        }

        setSupportActionBar(binding.mainToolBar.toolbar)
    }

    private fun updateName(userName: String) {
        val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(userName).build()

        auth.currentUser!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i(TAG, "User profile updated.")
                }
            }

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

            //When clicked on list button
            R.id.action_list ->{
                startActivity(Intent(applicationContext, GridRecyclerActivity::class.java))
                return true
            }

            //When  clicked on User button
            R.id.action_user_profile -> {
                startActivity(Intent(applicationContext, UserProfileActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout()
    {
        auth.signOut()
        finish()
        startActivity(Intent(this, StyledSigninActivity::class.java))
    }


}