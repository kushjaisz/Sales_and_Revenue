package com.example.milkrevenuetracker.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.milkrevenuetracker.db.MilkDao
import com.example.milkrevenuetracker.db.MilkDatabase
import com.example.milkrevenuetracker.others.Constants.Companion.BUFFALO_MILK_RATE
import com.example.milkrevenuetracker.others.Constants.Companion.COW_MILK_RATE
import com.example.milkrevenuetracker.others.Constants.Companion.DATABASE_NAME
import com.example.milkrevenuetracker.others.Constants.Companion.HAVE_BUFFALO
import com.example.milkrevenuetracker.others.Constants.Companion.HAVE_COW
import com.example.milkrevenuetracker.others.Constants.Companion.KEY_FIRST_TIME_TOGGLE
import com.example.milkrevenuetracker.others.Constants.Companion.KEY_NAME
import com.example.milkrevenuetracker.others.Constants.Companion.KEY_ORG
import com.example.milkrevenuetracker.others.Constants.Companion.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDb(app: Application): MilkDatabase {
        return Room.databaseBuilder(app, MilkDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideMilkDao(db: MilkDatabase): MilkDao {
        return db.getMilkDao()
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(app: Application) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPreferences: SharedPreferences) =
        sharedPreferences.getString(KEY_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideOrg(sharedPreferences: SharedPreferences) =
        sharedPreferences.getString(KEY_ORG, "")?:""

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPreferences: SharedPreferences) = sharedPreferences.getBoolean(
        KEY_FIRST_TIME_TOGGLE, true
    )

    @Singleton
    @Provides
    fun provideHavebuffalo(sharedPreferences: SharedPreferences) = sharedPreferences.getString(
         HAVE_BUFFALO, "")?:""

    @Singleton
    @Provides
    fun buffaloMilkRate(sharedPreferences: SharedPreferences) = sharedPreferences.getString(
        BUFFALO_MILK_RATE, ""
    )?:""

    @Singleton
    @Provides
    fun provideHaveCow(sharedPreferences: SharedPreferences) = sharedPreferences.getString(
        HAVE_COW, "")?:""



    @Singleton
    @Provides
    fun cowMilkRate(sharedPreferences: SharedPreferences) = sharedPreferences.getString(
        COW_MILK_RATE, ""
    )?:""



}