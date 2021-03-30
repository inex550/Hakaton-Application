package com.example.hakaton_bastion.ui.guard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hakaton_bastion.R
import com.example.hakaton_bastion.Repository
import com.example.hakaton_bastion.databinding.ActivityGuardPlaceBinding
import com.example.hakaton_bastion.databinding.ActivityMainBinding
import com.example.hakaton_bastion.models.network.Point
import com.example.hakaton_bastion.nfc.NfcActivity
import com.example.hakaton_bastion.ui.add_place_activity.PointsAdapter

class GuardPlaceActivity : NfcActivity() {

    private lateinit var binding: ActivityGuardPlaceBinding

    private var id: Int = -1

    private lateinit var points: MutableList<Point>

    private val adapter = PointsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuardPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getIntExtra("id", -1)

        binding.nameTv.text = Repository.places[id].title

        binding.pointsListRv.layoutManager = LinearLayoutManager(this)
        binding.pointsListRv.adapter = adapter

        points = Repository.places[id].points
    }

    override fun onResume() {
        super.onResume()
        adapter.data = points
    }

    override fun onIdentifierLoad(id: String) {
        val pointId = points.indexOfFirst { point -> point.nfcTag == id }

        if (pointId == -1)
            return

        points.removeAt(pointId)
        adapter.notifyItemRemoved(pointId)
    }
}