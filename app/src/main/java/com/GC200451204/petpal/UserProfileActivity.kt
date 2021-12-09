package com.GC200451204.petpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import com.example.petpal.R
import com.example.petpal.databinding.ActivityUserProfileBinding
import com.google.firebase.auth.FirebaseAuth

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUserProfileBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        //enable the scrollbars
//        binding.termsTextView.movementMethod = ScrollingMovementMethod()

        //ensure we have an authenticated user
        if (auth.currentUser == null)
            logout()
        else{
            auth.currentUser?.let{ user->
                binding.textViewUserName.text = user.displayName
                binding.textViewEmailID.text = user.email
                binding.userProfileDetails.text = "User Details"
            }
        }

        //Logout the user when logout button is pressed
        binding.logout.setOnClickListener {
            logout()
        }


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