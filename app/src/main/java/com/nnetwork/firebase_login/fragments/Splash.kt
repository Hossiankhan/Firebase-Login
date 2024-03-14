package com.nnetwork.firebase_login.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nnetwork.firebase_login.R
import com.nnetwork.firebase_login.databinding.FragmentSplashBinding


class Splash : Fragment() {

    lateinit var binding: FragmentSplashBinding

    private val Splash_time_out: Long = 1000


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSplashBinding.inflate(inflater,container,false)




        Handler().postDelayed({
            findNavController().navigate(R.id.action_splash_to_welcome)
        },Splash_time_out)


        return binding.root

    }

    
}