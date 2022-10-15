package com.example.sleeptracker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepDao {
    @Query("SELECT * FROM sleep2_table")
    fun getAll(): Flow<List<SleepEntity2>>

    @Insert
    fun insert(entry: SleepEntity2)
}