package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.entity.ProductItem

@Dao
interface AppDao {

    @Transaction
    suspend fun insertAndDeleteAll(list: List<ProductItem>) {
        deleteAll()
        insertProducts(list)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(list: List<ProductItem>)

    @Query("SELECT * FROM product")
    suspend fun getProducts(): List<ProductItem>

    @Query("DELETE FROM product")
    suspend fun deleteAll()
}
