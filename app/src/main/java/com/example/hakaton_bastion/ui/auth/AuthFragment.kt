package com.example.hakaton_bastion.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hakaton_bastion.R
import com.example.hakaton_bastion.databinding.FragmentAuthBinding
import com.example.hakaton_bastion.models.request.PinCode

class AuthFragment(
    private val successFunc: () -> Unit
): Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding: FragmentAuthBinding get() = _binding!!

    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this).get(AuthViewModel::class.java)
    }

    private lateinit var ovals: List<View>

    private var pinCode: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)

        viewModel.token.observe(this) { token ->
            if (token == null) {
                Toast.makeText(requireContext(), "Не правильный пин-код", Toast.LENGTH_SHORT).show()
                return@observe
            }

            successFunc()
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(requireContext(), "Ошибка: $error", Toast.LENGTH_SHORT).show()
        }

        viewModel.loadingState.observe(this) {
            binding.progressPb.visibility = if (it) View.VISIBLE else View.GONE
        }

        val btns = listOf(binding.pinBtn0, binding.pinBtn1, binding.pinBtn2, binding.pinBtn3, binding.pinBtn4, binding.pinBtn5, binding.pinBtn6, binding.pinBtn7, binding.pinBtn8, binding.pinBtn9)
        ovals = listOf(binding.oval1, binding.oval2, binding.oval3, binding.oval4, binding.oval5)

        for ((i, btn) in btns.withIndex())
            btn.setOnClickListener {
                if (pinCode.length < 5) {
                    pinCode += i.toString()
                    drawOvals(pinCode.length)
                }
            }

        binding.clearBtn.setOnClickListener {
            ovals.forEach { oval -> oval.setBackgroundResource(R.drawable.white_oval_stroke) }
            pinCode = ""
        }

        binding.okBtn.setOnClickListener {
            if (pinCode.length < 5) {
                Toast.makeText(requireContext(), "Необходимо ввести 5 цифр", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.signIn(PinCode(pinCode))
        }

        return binding.root
    }

    private fun drawOvals(count: Int) {
        for (i in 0 until count)
            ovals[i].setBackgroundResource(R.drawable.white_oval)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}