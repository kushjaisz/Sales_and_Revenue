package com.example.milkrevenuetracker.ui.ViewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.milkrevenuetracker.db.MilkEntity
import com.example.milkrevenuetracker.repo.MainRepo
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(val mainRepo: MainRepo):ViewModel() {
    val milkall = mainRepo.getall()
    val milkSeven = mainRepo.getseven()
    val milkThirty = mainRepo.getlastThirtyDays()
    val getlastYearDays = mainRepo.getlastYearDays()
    val getlastDays = mainRepo.getlastDays()


    var totalPriceLastDay = mainRepo.totalPriceLastDay()
    var totalPriceLastMonthly = mainRepo.totalPriceLastMonthly()
    var totalPriceLastweek = mainRepo.totalPriceLastweek()
    var totalPriceLastYear = mainRepo.totalPriceLastYear()

    var totalQTYLastDay = mainRepo.totalQTYLastDay()
    var totalQTYLastMonthly = mainRepo.totalQTYLastMonthly()
    var totalQTYLastweek = mainRepo.totalQTYLastweek()
    var totalQtyLastYear = mainRepo.totalQtyLastYear()

    var totalCountLastDay = mainRepo.totalCountLastDay()
    var totalCountLastMonthly = mainRepo.totalCountLastMonthly()
    var totalCountLastweek = mainRepo.totalCountLastweek()
    var totalCountLastYear = mainRepo.totalCountLastYear()


    val milkData = MediatorLiveData<List<MilkEntity>>()

    fun insertRun(milkEntity: MilkEntity) = viewModelScope.launch {
        mainRepo.insertSell(milkEntity)
    }

    init {
        milkData.addSource(milkall) { result ->
//            if(sortType == SortType.DATE) {
                result?.let { milkData.value = it }
//            }
        }
        milkData.addSource(milkSeven) { result ->
//            if(sortType == SortType.DATE) {
                result?.let { milkData.value = it }
//            }
        }
        milkData.addSource(milkThirty) { result ->
//            if(sortType == SortType.DATE) {
                result?.let { milkData.value = it }
//            }
        }
        milkData.addSource(getlastYearDays) { result ->
//            if(sortType == SortType.DATE) {
                result?.let { milkData.value = it }
//            }
        }
        milkData.addSource(getlastDays) { result ->
//            if(sortType == SortType.DATE) {
                result?.let { milkData.value = it }
//            }
        }

    }

    fun getall(){
        milkall.value?.let { milkData.value = it }

    }
    fun getseven(){
        milkSeven.value?.let { milkData.value = it }

    }
    fun getthirty(){
        milkThirty.value?.let { milkData.value = it }

    }
    fun getlastYearDays(){
        getlastYearDays.value?.let { milkData.value = it }

    }
    fun getlastDays(){
        getlastDays.value?.let { milkData.value = it }

    }


}