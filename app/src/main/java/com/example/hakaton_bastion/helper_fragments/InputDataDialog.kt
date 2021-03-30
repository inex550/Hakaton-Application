package com.example.hakaton_bastion.helper_fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.hakaton_bastion.R
import com.example.hakaton_bastion.databinding.InputDataDialogBinding

class InputDataDialog(
    private val message: String,
    private val hint: String
): DialogFragment() {

    private var _binding: InputDataDialogBinding? = null
    private val binding: InputDataDialogBinding get() = _binding!!

    private lateinit var onDataEntered: (String) -> Unit
    fun setOnDataEntered(func: (String) -> Unit) {
        onDataEntered = func
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = InputDataDialogBinding.inflate(LayoutInflater.from(context))

        binding.dataEt.hint = hint

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Внимание")
            .setMessage(message)
            .setView(binding.root)
            .setPositiveButton("Ок", null)
            .setNegativeButton("Отмена", null)
            .show()

        val btnPos = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        btnPos.setOnClickListener {
            val data = binding.dataEt.text.toString()
            onDataEntered(data)
        }

        return dialog
    }

    fun setError(error: String) {
        binding.errorTv.text = error
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}