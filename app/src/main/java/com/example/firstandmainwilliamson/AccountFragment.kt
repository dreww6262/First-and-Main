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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.bumptech.glide.Glide


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
        val model: MyViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        val usernameInput = v.findViewById<TextView>(R.id.usernameInput)
        val passwordInput = v.findViewById<TextView>(R.id.passwordInput)
        val button: Button = v.findViewById(R.id.submitButton)

        var passwordEntered = false

        model.getUser().observe(viewLifecycleOwner, Observer { it ->
            if (it != null) {
                Navigation.findNavController(v).navigate(R.id.accountDetails)
            }
            if (it == null && passwordEntered) {
                val toast: Toast = Toast.makeText(context, "Your username and or password is incorrect", Toast.LENGTH_LONG)
                toast.show()
            }
        })

        button.setOnClickListener {
            passwordEntered = true
            when {
                usernameInput.text.isNullOrEmpty() -> {
                    val toast: Toast = Toast.makeText(context, "Enter a username!", Toast.LENGTH_SHORT)
                    toast.show()
                }
                passwordInput.text.isNullOrEmpty() -> {
                    val toast: Toast = Toast.makeText(context, "Enter a password!", Toast.LENGTH_SHORT)
                    toast.show()
                }
                else -> {
                    val success = model.startSignIn(usernameInput.text.toString(), passwordInput.text.toString())
                    usernameInput.text = ""
                    passwordInput.text = ""
                }
            }
        }

        v.findViewById<TextView>(R.id.createAccountText).setOnClickListener{
            Navigation.findNavController(v).navigate(R.id.createAccountFragment)
        }

        return v
    }

}
