package com.example.hakaton_bastion.ui.capitan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hakaton_bastion.Repository
import com.example.hakaton_bastion.databinding.ActivityCapitanBinding
import com.example.hakaton_bastion.helper_fragments.InputDataDialog
import com.example.hakaton_bastion.models.network.Place
import com.example.hakaton_bastion.ui.add_place_activity.AddNewPlaceActivity

class CapitanActivity : AppCompatActivity(), PlacesAdapter.ItemClickListener {

    private lateinit var binding: ActivityCapitanBinding

    private val viewModel: CapitanViewModel by lazy {
        ViewModelProvider(this).get(CapitanViewModel::class.java)
    }

    private var iddPlaceName: InputDataDialog = InputDataDialog("Введите название места", "Название")

    private val adapter = PlacesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCapitanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.placeListRv.layoutManager = LinearLayoutManager(this)
        binding.placeListRv.adapter = adapter

        viewModel.loadPlaces()

        viewModel.places.observe(this) { places ->
            adapter.data = places
        }

        iddPlaceName.setOnDataEntered {
            if (it.isEmpty()) {
                iddPlaceName.setError("Ничего не введено")
                return@setOnDataEntered
            }

            Repository.places.add(Place(it, mutableListOf()))

            val intent = Intent(this, AddNewPlaceActivity::class.java)
            intent.putExtra("id", Repository.places.size - 1)
            startActivity(intent)

            iddPlaceName.dismiss()
        }

        binding.addPlaceBtn.setOnClickListener {
            iddPlaceName.show(supportFragmentManager, null)
        }
    }

    override fun onItemClicked(pos: Int) {
        val intent = Intent(this, AddNewPlaceActivity::class.java)
        intent.putExtra("id", pos)
        startActivity(intent)
    }
}