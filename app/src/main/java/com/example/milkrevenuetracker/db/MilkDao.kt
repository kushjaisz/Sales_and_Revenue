package com.example.milkrevenuetracker.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MilkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(milkEntity: MilkEntity)

    @Delete
    suspend fun deleteRun(milkEntity: MilkEntity)



    @Query("SELECT * FROM Milk_Sell")
    fun getall(): LiveData<List<MilkEntity>>

    @Query("SELECT SUM(price) FROM MILK_SELL")
    fun totalPrice(): LiveData<Int>
    @Query("SELECT SUM(milkQty) FROM MILK_SELL")
    fun totalQty(): LiveData<Int>

    @Query("SELECT AVG(price) FROM MILK_SELL")
    fun avgPrice(): LiveData<Float>
    @Query("SELECT AVG(milkQty) FROM MILK_SELL")
    fun avgQty(): LiveData<Float>

    @Query("SELECT COUNT(id) FROM Milk_Sell")
    fun getRowCount(): LiveData<Int?>? //with LiveData

    @Query("SELECT * from Milk_Sell WHERE CAST((timeInMillis / 1000) AS INTEGER) BETWEEN strftime('%s','now','-7 days') AND strftime('%s','now')  ORDER BY id DESC;")
    fun getlastSevenDays(): LiveData<List<MilkEntity>>

    @Query("SELECT * from Milk_Sell WHERE CAST((timeInMillis / 1000) AS INTEGER) BETWEEN strftime('%s','now','-30 days') AND strftime('%s','now')  ORDER BY id DESC;")
    fun getlastThirtyDays(): LiveData<List<MilkEntity>>


    @Query("SELECT * from Milk_Sell WHERE CAST((timeInMillis / 1000) AS INTEGER) BETWEEN strftime('%s','now','-365 days') AND strftime('%s','now')  ORDER BY id DESC;")
    fun getlastYearDays(): LiveData<List<MilkEntity>>

    @Query("SELECT * from Milk_Sell WHERE CAST((timeInMillis / 1000) AS INTEGER) BETWEEN strftime('%s','now','-1 days') AND strftime('%s','now')  ORDER BY id DESC;")
    fun getlastDays(): LiveData<List<MilkEntity>>



    @Query("SELECT SUM(price) FROM MILK_SELL WHERE CAST((timeInMillis / 1000) AS INTEGER) BETWEEN strftime('%s','now','-1 days') AND strftime('%s','now')  ORDER BY id DESC;")
    fun totalPriceLastDay(): LiveData<Int>
    @Query("SELECT SUM(price) FROM MILK_SELL WHERE CAST((timeInMillis / 1000) AS INTEGER) BETWEEN strftime('%s','now','-30 days') AND strftime('%s','now')  ORDER BY id DESC;")
    fun totalPriceLastMonthly(): LiveData<Int>
    @Query("SELECT SUM(price) FROM MILK_SELL WHERE CAST((timeInMillis / 1000) AS INTEGER) BETWEEN strftime('%s','now','-7 days') AND strftime('%s','now')  ORDER BY id DESC;")
    fun totalPriceLastweek(): LiveData<Int>
    @Query("SELECT SUM(price) FROM MILK_SELL WHERE CAST((timeInMillis / 1000) AS INTEGER) BETWEEN strftime('%s','now','-365 days') AND strftime('%s','now')  ORDER BY id DESC;")
    fun totalPriceLastYear(): LiveData<Int>



    @Query("SELECT SUM(milkQty) FROM MILK_SELL WHERE CAST((timeInMillis / 1000) AS INTEGER) BETWEEN strftime('%s','now','-1 days') AND strftime('%s','now')  ORDER BY id DESC;")
    fun totalQTYLastDay(): LiveData<Int>
    @Query("SELECT SUM(milkQty) FROM MILK_SELL WHERE CAST((timeInMillis / 1000) AS INTEGER) BETWEEN strftime('%s','now','-30 days') AND strftime('%s','now')  ORDER BY id DESC;")
    fun totalQTYLastMonthly(): LiveData<Int>
    @Query("SELECT SUM(milkQty) FROM MILK_SELL WHERE CAST((timeInMillis / 1000) AS INTEGER) BETWEEN strftime('%s','now','-7 days') AND strftime('%s','now')  ORDER BY id DESC;")
    fun totalQTYLastweek(): LiveData<Int>
    @Query("SELECT SUM(milkQty) FROM MILK_SELL WHERE CAST((timeInMillis / 1000) AS INTEGER) BETWEEN strftime('%s','now','-365 days') AND strftime('%s','now')  ORDER BY id DESC;")
    fun totalQtyLastYear(): LiveData<Int>



    @Query("SELECT COUNT(id) FROM MILK_SELL WHERE CAST((timeInMillis / 1000) AS INTEGER) BETWEEN strftime('%s','now','-1 days') AND strftime('%s','now')  ORDER BY id DESC;")
    fun totalCountLastDay(): LiveData<Int>
    @Query("SELECT COUNT(id) FROM MILK_SELL WHERE CAST((timeInMillis / 1000) AS INTEGER) BETWEEN strftime('%s','now','-30 days') AND strftime('%s','now')  ORDER BY id DESC;")
    fun totalCountLastMonthly(): LiveData<Int>
    @Query("SELECT COUNT(id) FROM MILK_SELL WHERE CAST((timeInMillis / 1000) AS INTEGER) BETWEEN strftime('%s','now','-7 days') AND strftime('%s','now')  ORDER BY id DESC;")
    fun totalCountLastweek(): LiveData<Int>
    @Query("SELECT COUNT(id) FROM MILK_SELL WHERE CAST((timeInMillis / 1000) AS INTEGER) BETWEEN strftime('%s','now','-365 days') AND strftime('%s','now')  ORDER BY id DESC;")
    fun totalCountLastYear(): LiveData<Int>
}