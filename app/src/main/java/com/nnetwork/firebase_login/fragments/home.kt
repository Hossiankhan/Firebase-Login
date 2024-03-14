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
import com.nnetwork.firebase_login.databinding.FragmentHomeBinding


class home : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private lateinit var auth: FirebaseAuth
    lateinit var firebaseUser:FirebaseUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        auth = FirebaseAuth.getInstance()

        binding.logoutbtn.setOnClickListener {
            singOut()
        }

        return binding.root
    }

    private fun singOut() {
        auth.signOut().apply { findNavController().navigate(R.id.action_home_to_welcome) }
    }


}