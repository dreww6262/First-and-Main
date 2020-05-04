package com.example.firstandmainwilliamson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.module.AppGlideModule
import kotlinx.android.synthetic.main.fragment_account.*


/**
 * A simple [Fragment] subclass.
 */
class AccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_account, container, false)
        val imageView = v.findViewById<ImageView>(R.id.accountImage)
        Glide.with(this)
            .load("https://images.squarespace-cdn.com/content/v1/586c5190e4fcb562283a11cd/1494815059119-" +
                    "89NBXHL3HL0O6Y8WLX85/ke17ZwdGBToddI8pDm48kLxnK526YWAH1qleWz-y7AFZw-zPPgdn4jUwVcJE1ZvWEtT5uB" +
                    "SRWt4vQZAgTJucoTqqXjS3CfNDSuuf31e0tVH33scGBZjC30S7EYewNF5iKKwhonf2ThqWWOBkLKnojuqYeU1KwPvsAK7Tx5ND4WE/image-asset.jpeg")
            .override(512, 512)
            .into(imageView)

        val usernameInput = v.findViewById<TextView>(R.id.usernameInput)
        val passwordInput = v.findViewById<TextView>(R.id.passwordInput)
        val button: Button = v.findViewById(R.id.submitButton)
        button.setOnClickListener {
            Navigation.findNavController(v).navigate(R.id.accountDetails)
            usernameInput.text = ""
            passwordInput.text = ""
        }

        return v
    }

}
