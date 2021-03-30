package com.example.hakaton_bastion.ui.guard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hakaton_bastion.R
import com.example.hakaton_bastion.Repository
import com.example.hakaton_bastion.databinding.ActivityGuardBinding
import com.example.hakaton_bastion.ui.capitan.PlacesAdapter

class GuardActivity : AppCompatActivity(), PlacesAdapter.ItemClickListener {

    lateinit var binding: ActivityGuardBinding

    private val adapter = PlacesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.placeListRv.layoutManager = LinearLayoutManager(this)
        binding.placeListRv.adapter = adapter

        adapter.data = Repository.places
    }

    override fun onItemClicked(pos: Int) {
        val intent = Intent(this, GuardPlaceActivity::class.java)
        intent.putExtra("id", pos)
        startActivity(intent)
    }
}