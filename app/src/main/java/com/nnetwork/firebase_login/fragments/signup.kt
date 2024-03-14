package com.nnetwork.firebase_login.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.nnetwork.firebase_login.R
import com.nnetwork.firebase_login.databinding.FragmentSignupBinding


class signup : Fragment() {

    lateinit var binding: FragmentSignupBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentSignupBinding.inflate(inflater,container,false)


        binding.backbutton.setOnClickListener {
            findNavController().popBackStack()

        }
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )

        binding.allogin.setOnClickListener {
            findNavController().navigate(R.id.action_signup_to_login)
        }

        binding.singupbtn.setOnClickListener {
            val email = binding.Email.text.toString().trim()
            val password = binding.passwo.text.toString().trim()

            auth = FirebaseAuth.getInstance()

            if (validateEmail() && validatePassword()) {
                loginUser(email, password)
            } else {
                Toast.makeText(requireContext(), "Invalid email or password", Toast.LENGTH_SHORT)
                    .show()
            }

        }


        return binding.root


    }

    private fun loginUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_signup_to_home)
                } else {
                    Toast.makeText(context, "Sing Up failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun validateEmail(): Boolean {
        val email = binding.Email.text.toString().trim()

        return if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.Email.error = "Enter a valid email address"
            false
        } else {
            binding.Email.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = binding.passwo.text.toString().trim()

        return if (password.isEmpty() || password.length < 16) {
            binding.passwo.error = "Password must be at least 16 characters long"
            false
        } else {
            binding.passwo.error = null
            true
        }
    }
}


