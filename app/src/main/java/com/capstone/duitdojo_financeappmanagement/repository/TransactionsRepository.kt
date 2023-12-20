package com.capstone.duitdojo_financeappmanagement.repository

import com.capstone.duitdojo_financeappmanagement.data.model.TransactionModel
import com.capstone.duitdojo_financeappmanagement.data.room.TransactionsDatabase
import kotlinx.coroutines.flow.Flow

class TransactionsRepository(private val db: TransactionsDatabase) {
    suspend fun insert(transaction: TransactionModel) = db.transactionsDao().insert(transaction)

    suspend fun update(transaction: TransactionModel) = db.transactionsDao().update(transaction)

    suspend fun delete(transaction: TransactionModel) = db.transactionsDao().delete(transaction)

    fun getAllTransactions(): Flow<List<TransactionModel>> =
        db.transactionsDao().getAllTransactions()

    fun getTransactionById(id: Int): Flow<TransactionModel> =
        db.transactionsDao().getTransactionById(id)
}