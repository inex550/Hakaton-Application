package com.example.hakaton_bastion.ui.add_place_activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hakaton_bastion.databinding.PlacesListItemBinding
import com.example.hakaton_bastion.databinding.PointsListItemBinding
import com.example.hakaton_bastion.models.network.Point

class PointsAdapter: RecyclerView.Adapter<PointsAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: PointsListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(point: Point) {
            binding.infoTv.text = point.info
            binding.nfcTv.text = point.nfcTag
        }
    }

    var data = mutableListOf<Point>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PointsListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val point = data[position]
        holder.bind(point)
    }

    override fun getItemCount(): Int = data.size
}