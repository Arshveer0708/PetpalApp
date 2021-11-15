package com.example.petpal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class PetListViewModel : ViewModel() {
    //this will hold a mutable list of PetDetails objects
    private val pets = MutableLiveData<List<PetDetails>>()

    init{
        loadPets()
    }

    /**
     * This method will load the PetDetails objects from Firebase.FireStore
     */
    private fun loadPets()
    {
        val db = FirebaseFirestore.getInstance().collection("pets")
            .orderBy("petName", Query.Direction.ASCENDING)

        db.addSnapshotListener{ documents, exception ->

            //if there is an exception - let's log it
            exception?.let {
                Log.i("DB_Response", "Listen failed : "+ exception)
                return@addSnapshotListener
            }

            Log.i("DB_Response", "# of elements returned: ${documents?.size()}")

            //create an array list of PetDetails objects that will be used to
            //populate the MutableLiveData variable called pets
            val petList = ArrayList<PetDetails>()

            //loop over the documents from the DB and create PetDetails objects
            documents?.let{
                for (document in documents)
                {
                    try {
                        val pet = document.toObject(PetDetails::class.java)
                        petList.add(pet)
                    }catch(e : Exception)
                    {
                        Log.i("DB_Response", document.toString())
                    }

                }
            }
            pets.value = petList
        }
    }

    fun getPets() : LiveData<List<PetDetails>>
    {
        return pets
    }
}