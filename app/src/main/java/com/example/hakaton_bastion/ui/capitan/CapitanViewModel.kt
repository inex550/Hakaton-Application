package com.example.hakaton_bastion.ui.capitan

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.hakaton_bastion.Repository
import com.example.hakaton_bastion.models.network.Place

class CapitanViewModel: ViewModel() {

    val places = MediatorLiveData<List<Place>>()

    fun loadPlaces() {
        places.value = Repository.places
    }
}