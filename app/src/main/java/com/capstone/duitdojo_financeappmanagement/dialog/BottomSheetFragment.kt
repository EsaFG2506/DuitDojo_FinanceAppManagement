package com.capstone.duitdojo_financeappmanagement.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.capstone.duitdojo_financeappmanagement.R
import com.capstone.duitdojo_financeappmanagement.databinding.FragmentBottomSheetBinding
import com.capstone.duitdojo_financeappmanagement.ui.MainActivity
import com.capstone.duitdojo_financeappmanagement.ui.viewmodel.TransactionsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch


class BottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var viewModel: TransactionsViewModel
    lateinit var binding: FragmentBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBottomSheetBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        //setUpView()
    }

    private fun setUpView() = with(binding) {
        lifecycleScope.launch {

            btnEUR.setOnClickListener {
                viewModel.selectedCurrency.value = "EUR"
                dismiss()
            }

            btnUsd.setOnClickListener {
                viewModel.selectedCurrency.value = "USD"
                dismiss()
            }

            btnGbp.setOnClickListener {
                viewModel.selectedCurrency.value = "GBP"
                dismiss()
            }

            btnIdr.setOnClickListener {
                viewModel.selectedCurrency.value = "IDR"
                dismiss()
            }

            btnYen.setOnClickListener {
                viewModel.selectedCurrency.value = "JPY"
                dismiss()
            }

            btnRUB.setOnClickListener {
                viewModel.selectedCurrency.value = "RUB"
                dismiss()
            }

            btnKRW.setOnClickListener {
                viewModel.selectedCurrency.value = "KRW"
                dismiss()
            }

            btnSEK.setOnClickListener {
                viewModel.selectedCurrency.value = "SEK"
                dismiss()
            }
        }
    }

}