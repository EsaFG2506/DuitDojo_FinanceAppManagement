package com.capstone.duitdojo_financeappmanagement.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.capstone.duitdojo_financeappmanagement.data.model.MenuItem

@Database(entities = [MenuItem::class], version = 3, exportSchema = false)
abstract class PhotoDB : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
    companion object {
        @Volatile
        private var INSTANCE: PhotoDB? = null
        @JvmStatic
        fun getDatabase(context: Context): PhotoDB {
            if (INSTANCE == null) {
                synchronized(PhotoDB::class.java) {
                    val MIGRATION_2_3: Migration = object : Migration(2, 3) {
                        override fun migrate(database: SupportSQLiteDatabase) {
                            database.execSQL("ALTER TABLE stories ADD COLUMN description TEXT")
                        }
                    }
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        PhotoDB::class.java, "story.db")
                        .fallbackToDestructiveMigration()
                        .addMigrations(MIGRATION_2_3)
                        .build()
                }
            }
            return INSTANCE as PhotoDB
        }
    }
}