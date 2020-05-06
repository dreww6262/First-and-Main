package com.example.firstandmainwilliamson

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore

class MyViewModel() : ViewModel() {
    private val firestoreDB = FirebaseFirestore.getInstance()
    private val firebaseAuth = FirebaseAuth.getInstance()
    private var user: MutableLiveData<FirebaseUser> = MutableLiveData()
    private var customToken: String? = null
    private val tag = "FIRESTORE_VIEW_MODEL"
    private var savedStores: MutableLiveData<List<StoreItem>> = MutableLiveData()
    private var savedPromos: MutableLiveData<List<PromoItem>> = MutableLiveData()
    //private val savedStateHandle = state

    fun getStores(): LiveData<List<StoreItem>> {
        val storeCollection = firestoreDB.collection("stores")
        storeCollection.addSnapshotListener(EventListener { value, e ->
            if (e != null) {
                Log.w(tag, "Listen failed.", e)
                //savedStores.value = null
                return@EventListener
            }
            val savedStoreList: MutableList<StoreItem> = mutableListOf()
            for (s in value!!) {
                val storeItem = s.toObject(StoreItem::class.java)
                savedStoreList.add(storeItem)
            }
            savedStores.value = savedStoreList
            //saveCurrentStores()
        })
        return savedStores
    }

    fun getPromos(): LiveData<List<PromoItem>> {
        val storeCollection = firestoreDB.collection("promos")
        storeCollection.addSnapshotListener(EventListener { value, e ->
            if (e != null) {
                Log.w(tag, "Listen failed.", e)
                //savedPromos.value = null
                return@EventListener
            }
            val savedPromoList: MutableList<PromoItem> = mutableListOf()
            for (s in value!!) {
                val promoItem = s.toObject(PromoItem::class.java)
                savedPromoList.add(promoItem)
            }
            savedPromos.value = savedPromoList
            //saveCurrentPromos()
        })
        return savedPromos
    }

    fun getUser(): MutableLiveData<FirebaseUser> {
        user.value = firebaseAuth.currentUser
        return user
    }

    fun startSignIn(email: String, password: String): Boolean {
        var successful = false
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                //log
                successful = true
                user.postValue(firebaseAuth.currentUser)
            } else {
                user.postValue(null)
            }
        }
        return successful
    }

    fun createAccount(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // empty
            }
            else {
                // empty
            }
        }
    }

    fun signOut() {
        firebaseAuth.signOut()
        user.postValue(null)
    }

    /*
    private fun saveCurrentPromos() {
        savedStateHandle.set("promoList", savedPromos)
    }
    private fun saveCurrentStores() {
        savedStateHandle.set("storeList", savedStores)
    }
    private fun getStoredPromos(): MutableLiveData<List<PromoItem>> {
        return savedStateHandle.get("promoList") ?: MutableLiveData()
    }
    private fun getStoredStores(): MutableLiveData<List<StoreItem>> {
        return savedStateHandle.get("storeList") ?: MutableLiveData()
    }

     */
}