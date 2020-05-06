@file:Suppress("DEPRECATION")

package com.example.firstandmainwilliamson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

/**
 * A simple [Fragment] subclass.
 */
class AccountDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_account_details, container, false)

        val button = v.findViewById<Button>(R.id.signOutButton)
        val model: MyViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        button.setOnClickListener {
            model.signOut()
            Navigation.findNavController(v).navigate(R.id.accountFragment)
        }
        val displayName = model.getUser().value?.displayName ?: "User"
        val dialogue = "Hello, $displayName"
        v.findViewById<TextView>(R.id.userText).text = dialogue

        return v
    }

}
