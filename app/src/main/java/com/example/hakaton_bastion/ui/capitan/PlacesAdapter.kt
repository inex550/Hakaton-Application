package com.example.hakaton_bastion.ui.capitan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hakaton_bastion.databinding.PlacesListItemBinding
import com.example.hakaton_bastion.models.response.Place

class PlacesAdapter: RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: PlacesListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(place: Place) {
            binding.titleTv.text = place.title
            binding.placesTv.text = place.places.joinToString { point -> "- ${point.title}\n" }
        }
    }

    var data = listOf<Place>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlacesListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = data[position]
        holder.bind(place)
    }

    override fun getItemCount(): Int = data.size
}