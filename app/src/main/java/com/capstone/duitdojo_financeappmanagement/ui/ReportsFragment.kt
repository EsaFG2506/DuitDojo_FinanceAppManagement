package com.capstone.duitdojo_financeappmanagement.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.capstone.duitdojo_financeappmanagement.R
import com.capstone.duitdojo_financeappmanagement.databinding.FragmentReportsBinding
import com.capstone.duitdojo_financeappmanagement.ui.adapter.ViewPagerAdapter
import com.capstone.duitdojo_financeappmanagement.ui.report.ExpenseReportFragment
import com.capstone.duitdojo_financeappmanagement.ui.report.IncomeReportFragment


class ReportsFragment : Fragment(R.layout.fragment_reports) {

    private lateinit var binding: FragmentReportsBinding
    private lateinit var adapter: ViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReportsBinding.bind(view)
        setUpTabs()
    }

    private fun setUpTabs() = with(binding) {

        adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(IncomeReportFragment(), getString(R.string.income))
        adapter.addFragment(ExpenseReportFragment(), getString(R.string.expenses))
        viewPager.adapter = adapter
        tabs.setupWithViewPager(binding.viewPager)

    }

}