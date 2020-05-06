package com.example.firstandmainwilliamson

import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class MyViewModel() : ViewModel() {
    private val firestoreDB = FirebaseFirestore.getInstance()
    private val firebaseAuth = FirebaseAuth.getInstance()
    private var user: MutableLiveData<FirebaseUser> = MutableLiveData()
    //private val savedStateHandle = state

    suspend fun getStores(): List<StoreItem> {
        try {
            val storeCollection = firestoreDB.collection("stores").get().await()
            return storeCollection.toObjects(StoreItem::class.java)
        }
        catch (e: Exception) {
            Log.e("ViewModel", "request to get stores failed", e)
        }
        return listOf()
    }

    suspend fun getPromos(): List<PromoItem> {
        try {
            val promoCollection = firestoreDB.collection("promos").get().await()
            return promoCollection.toObjects(PromoItem::class.java)
        }
        catch (e: Exception) {
            Log.e("ViewModel", "request to get promos failed", e)
        }
        return listOf()
    }

    fun getUser(): MutableLiveData<FirebaseUser> {
        user.value = firebaseAuth.currentUser
        return user
    }

    suspend fun startSignIn(email: String, password: String): Boolean {
        var successful = false
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            successful = true
        }
        catch (e: Exception) {
            Log.e("ViewModel", "Signin failed", e)
        }
        return successful
    }

    suspend fun createAccount(email: String, password: String): Boolean {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            true
        } catch (e: java.lang.Exception) {
            Log.e("ViewModel", "create account failed", e)
            false
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