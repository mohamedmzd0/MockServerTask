package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entity.ProductItem

@Database(
    entities = [ProductItem::class],
    version = 1
)
abstract class AppDatabase:RoomDatabase() {
    abstract fun appDao(): AppDao
}