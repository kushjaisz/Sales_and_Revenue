package com.example.milkrevenuetracker.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Milk_Sell")
data class MilkEntity(
    var custName: String,
    var milkQty: Int,
    var price: Int,
    var milkInvoiceNo : String,
    var milkType : String,
    var timeInMillis: Long = 0,
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

}