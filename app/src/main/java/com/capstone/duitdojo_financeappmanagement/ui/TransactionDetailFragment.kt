package com.capstone.duitdojo_financeappmanagement.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.capstone.duitdojo_financeappmanagement.R
import com.capstone.duitdojo_financeappmanagement.databinding.FragmentTransactionDetailBinding
import com.capstone.duitdojo_financeappmanagement.ui.viewmodel.TransactionsViewModel
import com.capstone.duitdojo_financeappmanagement.utils.currencyFormat
import kotlinx.coroutines.launch

class TransactionDetailFragment : Fragment(R.layout.fragment_transaction_detail) {

    private lateinit var binding: FragmentTransactionDetailBinding
    private val args: TransactionDetailFragmentArgs by navArgs()
    private lateinit var viewModel: TransactionsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTransactionDetailBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        (activity as AppCompatActivity).supportActionBar?.title = "Detail Transaction"
        observeData()
    }

    private fun observeData() = with(binding) {
        val transactionItem = viewModel.getTransactionById(args.transaction.id)
        lifecycleScope.launch {
            transactionItem.collect { transaction ->
                tvDetailsTitle.text = transaction.title
                tvDetailsCategory.text = getString(transaction.category.description)
                tvDetailsNotes.text = transaction.note
                tvDetailsType.text = transaction.transactionType
                tvDetailsDate.text = transaction.date
                tvDetailsAmount.text = currencyFormat(transaction.amount,viewModel.selectedCurrency.value)

                val action =
                    TransactionDetailFragmentDirections.actionTransactionDetailFragmentToEditTransactionFragment(
                        transaction
                    )
                fabEditTransaction.setOnClickListener { findNavController().navigate(action) }
            }
        }
    }

}