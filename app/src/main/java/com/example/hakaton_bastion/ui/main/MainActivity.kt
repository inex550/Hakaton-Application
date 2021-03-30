package com.example.hakaton_bastion.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hakaton_bastion.R
import com.example.hakaton_bastion.databinding.ActivityMainBinding
import com.example.hakaton_bastion.ui.auth.AuthFragment
import com.example.hakaton_bastion.ui.capitan.CapitanActivity
import com.example.hakaton_bastion.ui.guard.GuardActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val authFragment = AuthFragment { user ->
            val intent = Intent(this, when(user.status) {
                0 -> GuardActivity::class.java
                else -> CapitanActivity::class.java
            })

            startActivity(intent)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, authFragment)
            .commit()
    }
}