package com.capstone.duitdojo_financeappmanagement.utils

import android.content.Context
import com.capstone.duitdojo_financeappmanagement.R

class Constants ( context: Context) {

    companion object {
        const val API_KEY = "48ceb3d241cc4b2490e128d2482a75d2"
        const val BASE_URL = "https://newsapi.org/"
        const val PHOTO_URL = "https://duitdojomlmodel-oaqdmhxx7a-et.a.run.app/"
    }

    val TRANSACTION_TYPE = listOf(
        context.getString(R.string.income),
        context.getString(R.string.expenses)
    )

    val TRANSACTION_CATEGORY = arrayListOf(
        context.getString(TransactionCategory.Bills.description),
        context.getString(TransactionCategory.Food.description),
        context.getString(TransactionCategory.Education.description),
        context.getString(TransactionCategory.Entertainment.description),
        context.getString(TransactionCategory.Housing.description),
        context.getString(TransactionCategory.Health.description),
        context.getString(TransactionCategory.Travel.description),
        context.getString(TransactionCategory.Transportation.description),
        context.getString(TransactionCategory.Shopping.description),
        context.getString(TransactionCategory.Salary.description),
        context.getString(TransactionCategory.Investments.description),
        context.getString(TransactionCategory.Other.description)
    )
}