package com.capstone.duitdojo_financeappmanagement.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.capstone.duitdojo_financeappmanagement.data.model.TransactionModel
import com.capstone.duitdojo_financeappmanagement.data.room.converter.Converters


@Database(entities = [TransactionModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TransactionsDatabase : RoomDatabase() {
    abstract fun transactionsDao(): TransactionsDao

    companion object {
        @Volatile
        private var INSTANCE: TransactionsDatabase? = null

        fun getInstance(context: Context): TransactionsDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TransactionsDatabase::class.java,
                        "transactions_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance

                }
                return instance

            }

        }
    }

}