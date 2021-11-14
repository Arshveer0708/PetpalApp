package com.example.petpal


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import com.example.petpal.databinding.ActivityAddPetDetailsBinding
import com.google.firebase.firestore.FirebaseFirestore

class AddPetDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPetDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPetDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            if (binding.editTextPetName.text.toString()
                    .isNotEmpty() && binding.editTextPetBreed.text.toString()
                    .isNotEmpty() && binding.editPetAge.text.toString()
                    .isNotEmpty() && binding.editPetQualities.text.toString()
                    .isNotEmpty() && binding.editAddress.text.toString()
                    .isNotEmpty() && binding.editContactNumber.text.toString().isNotEmpty() &&
                binding.spinnerCategory.selectedItemPosition > 0
            ) {
                //create an instance of the restaurant
                val pet = PetDetails()
                pet.petName = binding.editTextPetName.text.toString()
                pet.petBreed = binding.editTextPetBreed.text.toString()
                pet.petAge = binding.editTextPetName.text.toString()
                pet.petQualities = binding.editPetQualities.text.toString()
                pet.address = binding.editAddress.text.toString()
                pet.contact = binding.editContactNumber.text.toString()
                pet.petCategory = binding.spinnerCategory.selectedItem.toString()

                //store the pet detaills in Firebase-Firestore

                //1.  get an ID from Firestore
                val db = FirebaseFirestore.getInstance().collection("pets")
                pet.id = db.document().id
//                Log.i("DB_Response","${restaurant.id}")

                //2. store the restaurant as a document
                db.document(pet.id!!).set(pet)
                    .addOnSuccessListener {
                        Toast.makeText(this, "New Pet Added", Toast.LENGTH_LONG).show()
                        binding.editTextPetName.setText("")
                        binding.editTextPetBreed.setText("")
                        binding.editTextPetName.setText("")
                        binding.editAddress.setText("")
                        binding.editContactNumber.setText("")
                        binding.editPetQualities.setText("")
                        binding.spinnerCategory.setSelection(0)
//                        startActivity(Intent(this, GridRecyclerActivity::class.java))
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(this, "DB Error", Toast.LENGTH_LONG).show()
//                        var message = exception.localizedMessage
//                        message?.let {
//                            Log.i("DB Message", message)
//                        }

                    }
            } else {
                Toast.makeText(this, "Restaurant name and rating required", Toast.LENGTH_LONG)
                    .show()
            }
        }

//        setSupportActionBar(binding.mainToolBar.toolbar)
    }

}