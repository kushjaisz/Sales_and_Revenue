package com.example.milkrevenuetracker.repo

import com.example.milkrevenuetracker.db.MilkDao
import com.example.milkrevenuetracker.db.MilkEntity
import javax.inject.Inject

class MainRepo @Inject constructor(val milkDao: MilkDao) {
    suspend fun insertSell(milk: MilkEntity) = milkDao.insertRun(milk)
    suspend fun deleteSell(milk: MilkEntity) = milkDao.insertRun(milk)
    fun getall() = milkDao.getall()
    fun getseven() = milkDao.getlastSevenDays()
    fun getlastYearDays() = milkDao.getlastYearDays()
    fun getlastDays() = milkDao.getlastDays()
    fun getlastThirtyDays() = milkDao.getlastThirtyDays()
    fun totalPrice() = milkDao.totalPrice()


    fun totalPriceLastDay() = milkDao.totalPriceLastDay()
    fun totalPriceLastMonthly() = milkDao.totalPriceLastMonthly()
    fun totalPriceLastweek() = milkDao.totalPriceLastweek()
    fun totalPriceLastYear() = milkDao.totalPriceLastYear()


    fun totalQTYLastDay() = milkDao.totalQTYLastDay()
    fun totalQTYLastMonthly() = milkDao.totalQTYLastMonthly()
    fun totalQTYLastweek() = milkDao.totalQTYLastweek()
    fun totalQtyLastYear() = milkDao.totalQtyLastYear()


    fun totalCountLastDay() = milkDao.totalCountLastDay()
    fun totalCountLastMonthly() = milkDao.totalCountLastMonthly()
    fun totalCountLastweek() = milkDao.totalCountLastweek()
    fun totalCountLastYear() = milkDao.totalCountLastYear()



    fun totalQty() = milkDao.totalQty()
    fun avgPrice() = milkDao.avgPrice()
    fun avgQty() = milkDao.avgQty()
    fun totalSell() = milkDao.getRowCount()

}