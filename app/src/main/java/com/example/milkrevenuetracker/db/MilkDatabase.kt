package com.example.milkrevenuetracker.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [MilkEntity::class],
    version = 1
)
abstract class MilkDatabase : RoomDatabase() {
    abstract fun getMilkDao(): MilkDao

}