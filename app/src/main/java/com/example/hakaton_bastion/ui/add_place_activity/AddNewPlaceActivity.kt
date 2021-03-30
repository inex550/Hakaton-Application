package com.example.hakaton_bastion.ui.add_place_activity

import android.app.AlertDialog
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hakaton_bastion.Repository
import com.example.hakaton_bastion.databinding.ActivityAddNewPlaceBinding
import com.example.hakaton_bastion.helper_fragments.InputDataDialog
import com.example.hakaton_bastion.models.network.Place
import com.example.hakaton_bastion.models.network.Point
import com.example.hakaton_bastion.nfc.NfcActivity
import com.example.hakaton_bastion.ui.capitan.PlacesAdapter

class AddNewPlaceActivity : NfcActivity() {

    private lateinit var binding: ActivityAddNewPlaceBinding

    private lateinit var loadingDialog: AlertDialog

    private var pointInfo: String? = null

    private var iddPointName: InputDataDialog = InputDataDialog("Введите информацию о точке, чтобы охранник не заблудился", "Ввод")

    private val adapter = PointsAdapter()

    private var placeId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        placeId = intent.getIntExtra("id", -1)

        binding.nameTv.text = Repository.places[placeId].title

        binding.pointsListRv.layoutManager = LinearLayoutManager(this)
        binding.pointsListRv.adapter = adapter

        iddPointName.setOnDataEntered {
            if (it.isEmpty()) {
                iddPointName.setError("Ничего не введено")
                return@setOnDataEntered
            }

            pointInfo = it

            loadingDialog = AlertDialog.Builder(this)
                .setTitle("Ожидание")
                .setMessage("Поднесите телефон к NFC метке")
                .setOnDismissListener { pointInfo = null }
                .create()

            loadingDialog.show()

            iddPointName.dismiss()
        }

        binding.addPointBtn.setOnClickListener {
            iddPointName.show(supportFragmentManager, null)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.data = Repository.places[placeId].points
    }

    override fun onIdentifierLoad(id: String) {
        if (pointInfo == null)
            return

        val point = Point(pointInfo!!, id, false)
        Repository.places[placeId].points.add(point)

        adapter.notifyItemInserted(adapter.itemCount)

        loadingDialog.dismiss()
    }
}