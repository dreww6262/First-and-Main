package com.example.firstandmainwilliamson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
        button.setOnClickListener {
            Navigation.findNavController(v).navigate(R.id.accountDetails)
        }

        return v
    }

}
