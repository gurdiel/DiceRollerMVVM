package com.mpd.pmdm.dicerollerconstraintlayout.core

import android.app.Application
import com.mpd.pmdm.dicerollerconstraintlayout.data.LocalRepository
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.RollsDatabase

//TENEMOS QUE DECLARARLA EN EL MANIFESTS ---->>> PARA PODER USARLA. ATENCIÓN!!!
//----no olvidarse de meter esto android:name=".core.DiceRollerApp"
class DiceRollerApp:Application() {
    //Exponemos el repositorio y no la base de datos


    private val database: RollsDatabase by lazy { RollsDatabase.getDataBase(this) }

    //así el view model puede usar el repositorio
    val localRepo: LocalRepository by lazy { LocalRepository(database) }


}