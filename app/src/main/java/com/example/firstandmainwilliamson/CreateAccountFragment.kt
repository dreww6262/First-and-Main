package com.example.firstandmainwilliamson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.bumptech.glide.Glide

/**
 * A simple [Fragment] subclass.
 */
class CreateAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_create_account, container, false)

        val imageView = v.findViewById<ImageView>(R.id.newAccountImage)
        Glide.with(this)
            .load("https://images.squarespace-cdn.com/content/v1/586c5190e4fcb562283a11cd/1494815059119-" +
                    "89NBXHL3HL0O6Y8WLX85/ke17ZwdGBToddI8pDm48kLxnK526YWAH1qleWz-y7AFZw-zPPgdn4jUwVcJE1ZvWEtT5uB" +
                    "SRWt4vQZAgTJucoTqqXjS3CfNDSuuf31e0tVH33scGBZjC30S7EYewNF5iKKwhonf2ThqWWOBkLKnojuqYeU1KwPvsAK7Tx5ND4WE/image-asset.jpeg")
            .override(512, 512)
            .into(imageView)
        val model: MyViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        val usernameInput = v.findViewById<TextView>(R.id.newAccountUsernameInput)
        val passwordInput = v.findViewById<TextView>(R.id.newAccountPasswordInput)
        val button: Button = v.findViewById(R.id.newAccountSubmitButton)
        button.setOnClickListener {
            if (usernameInput.text == "") {
                val toast: Toast = Toast.makeText(context, "Enter a username!", Toast.LENGTH_SHORT)
                toast.show()
            }
            else if (passwordInput.text == "") {
                val toast: Toast = Toast.makeText(context, "Enter a password!", Toast.LENGTH_SHORT)
                toast.show()
            }
            else {
                model.createAccount(usernameInput.text.toString(), passwordInput.text.toString())
                Navigation.findNavController(v).navigate(R.id.accountFragment)
            }
        }

        return v
    }

}
