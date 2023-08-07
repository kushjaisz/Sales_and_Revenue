package com.example.milkrevenuetracker.ui.ViewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.milkrevenuetracker.repo.MainRepo

class DashboardViewMOdel @ViewModelInject constructor(val mainRepo: MainRepo): ViewModel() {
    var totalPrice = mainRepo.totalPrice()
    var totalQty = mainRepo.totalQty()
    var totalPriceLastDay = mainRepo.totalPriceLastDay()
    var totalPriceLastMonthly = mainRepo.totalPriceLastMonthly()
    var totalPriceLastweek = mainRepo.totalPriceLastweek()
    var totalPriceLastYear = mainRepo.totalPriceLastYear()
    var avgSellPrice = mainRepo.avgPrice()
    var avgQty = mainRepo.avgQty()
    var totalSell = mainRepo.totalSell()

}