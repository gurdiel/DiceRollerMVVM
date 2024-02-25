package com.mpd.pmdm.dicerollerconstraintlayout.data.database

import android.provider.LiveFolders
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DiceRollsDao {

    @Query("Select * from dice_rolls order by id desc")
    fun getAllRolls(): LiveData<List<DiceRolls>> 
}