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
import com.google.firebase.auth.FirebaseUser
import com.nnetwork.firebase_login.R
import com.nnetwork.firebase_login.databinding.FragmentLoginBinding


class login : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)





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
        binding.forgetpass.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_forgottenpassword)
        }
        binding.singup.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_signup)
        }


        binding.loginbtn.setOnClickListener {
            val email = binding.editMail.text.toString().trim()
            val password = binding.editepass.text.toString().trim()


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

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_login_to_home)
                } else {
                    Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun validateEmail(): Boolean {
        val email = binding.editMail.text.toString().trim()

        return if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editMail.error = "Enter a valid email address"
            false
        } else {
            binding.editMail.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = binding.editepass.text.toString().trim()

        return if (password.isEmpty() || password.length < 16) {
            binding.editepass.error = "Password must be at least 16 characters long"
            false
        } else {
            binding.editepass.error = null
            true
        }
    }


}