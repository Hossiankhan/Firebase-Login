package com.nnetwork.firebase_login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.nnetwork.firebase_login.R
import com.nnetwork.firebase_login.databinding.FragmentLoginBinding
import com.nnetwork.firebase_login.databinding.FragmentWelcomeBinding


class welcome : Fragment() {
lateinit var binding: FragmentWelcomeBinding
    lateinit var firebaseUser: FirebaseUser
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding=FragmentWelcomeBinding.inflate(inflater,container,false)
        FirebaseAuth.getInstance().currentUser?.let {
            findNavController().navigate(R.id.action_welcome_to_home)
        }

        binding.singup.setOnClickListener {
            findNavController().navigate(R.id.action_welcome_to_signup)
        }
        binding.Login.setOnClickListener {
            findNavController().navigate(R.id.action_welcome_to_login)
        }

        return binding.root
    }


}