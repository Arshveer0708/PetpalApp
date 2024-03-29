package com.GC200451204.petpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.petpal.R
import com.example.petpal.databinding.ActivityEnterUserCredentialsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EnterUserCredentialsActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityEnterUserCredentialsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterUserCredentialsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        //make the user name field invisible unless it is a new user
        binding.userNameTextInputLayout.visibility = View.GONE

        //check if the user is already defined in Firebase.auth
        binding.nextButton.setOnClickListener {
            var email =binding.emailEditText.text.toString()
            if (email.isNotEmpty())
            {
                checkIfUserExists(email)
            }
        }
    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
//            reload();
            startActivity(Intent(this, GridRecyclerActivity::class.java))
        }
    }

    /**
     * This method will check to see if an email already exists in the Firebase.auth system
     */
    private fun checkIfUserExists(email : String){
        var password = binding.passwordEditText.text.toString()
        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { task ->
                task.result?.signInMethods?.isEmpty().let { newUser ->
                    if (newUser!!) {
                        Log.i("DB_Response", "$email is a new user")
                        binding.userNameTextInputLayout.visibility = View.VISIBLE
                        if (password.isNotEmpty())
                            createNewUser(email, password)
                        else
                            Toast.makeText(this, "Enter a password",Toast.LENGTH_LONG).show()

                    } else {
                        Log.i("DB_Response", "$email is an existing user")

                        //if the user's email is found AND password is not empty, attempt to login
                        if (password.isNotEmpty()) {
                            login(email, password)
                        }
                    }
                }
            }
    }

    private fun createNewUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.i("DB_Response", "$email - createUserWithEmail:success")
                    startActivity(Intent(this, UserProfileActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.i("DB_Response", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }


    private fun login(email : String, password : String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.i("DB_Response", "$email - signInWithEmail:success")
                    startActivity(Intent(this, UserProfileActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.i("DB_Response", "$email - signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed - renter email & password",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }


}