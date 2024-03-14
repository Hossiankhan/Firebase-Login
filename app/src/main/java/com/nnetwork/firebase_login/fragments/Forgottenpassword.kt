package com.nnetwork.firebase_login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.nnetwork.firebase_login.R
import com.nnetwork.firebase_login.databinding.FragmentForgottenpasswordBinding


class forgottenpassword : Fragment() {

    lateinit var binding:FragmentForgottenpasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentForgottenpasswordBinding.inflate(inflater,container,false)
        auth = FirebaseAuth.getInstance()

        binding.next.setOnClickListener {
            val email= binding.editMail.text.toString().trim()
            if (email.isNotEmpty()) {
                sendPasswordResetEmail(email)
            } else {
                Toast.makeText(requireContext(), "Enter your email address", Toast.LENGTH_SHORT)
                    .show()
            }
        }



        return binding.root
    }
    private fun sendPasswordResetEmail(email: String) {

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                  findNavController().navigate(R.id.action_forgottenpassword_to_login)
                } else {

                    Toast.makeText(
                        requireContext(),
                        "Failed to send password reset email. ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }


}