package com.example.hakaton_bastion.ui.capitan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hakaton_bastion.databinding.ActivityCapitanBinding

class CapitanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCapitanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCapitanBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}