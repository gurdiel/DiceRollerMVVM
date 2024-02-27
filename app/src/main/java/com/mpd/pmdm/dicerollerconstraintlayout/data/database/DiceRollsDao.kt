package com.mpd.pmdm.dicerollerconstraintlayout.data.database

import android.provider.LiveFolders
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

//DATA ACCESS OBJECT
//
@Dao
interface DiceRollsDao {
    @Query("Select * from dice_rolls order by id desc")
    fun getAllRolls(): LiveData<List<DiceRolls>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiceRoll(diceRoll: DiceRolls)
    //podemos hacer que nos devuelva el id Insertado.
    //hay que nombrarlas suspend no se pueden ejecutar desde el hilo principal

    @Update
    suspend fun updateDiceRoll(diceRoll: DiceRolls)

    @Query("Select * from dice_rolls where timestamp " +
            "between :sinceTimestamp and :toTimestamp order by id desc")
    fun getRollsBetweenTimes(sinceTimestamp: Long,toTimestamp:Long): LiveData<List<DiceRolls>>

    @Delete
    suspend fun deleteDiceRoll(diceRolls: DiceRolls)

    @Query("Delete from dice_rolls")
    suspend fun clearDiceRolls()

}