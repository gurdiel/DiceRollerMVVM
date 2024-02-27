package com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.mpd.pmdm.dicerollerconstraintlayout.core.DiceRollerApp
import com.mpd.pmdm.dicerollerconstraintlayout.data.LocalRepository
import com.mpd.pmdm.dicerollerconstraintlayout.ui.views.Dice
import kotlinx.coroutines.launch

class TwoDicesViewModel(val numSides: Int, val repository: LocalRepository): ViewModel() {

    private val dice1 = Dice(numSides)
    private val dice2 = Dice(numSides)

    //podemos crear observadores de estas instancias.
    val currentSideDice1: LiveData<Int> = dice1.currentSide
    val currentSideDice2: LiveData<Int> = dice2.currentSide

    fun rollDices(){
        dice1.roll()
        dice2.roll()
        //Guardamos el registro del lanzamiento en la BBDD
        //Liada por se los datos LiveData.....Video
        val cSD1 = currentSideDice1.value
        val cSD2 = currentSideDice2.value
        //TENEMOS QUE LANZAR UNA CORRUTINA Y SABER DESDE DONDE LA LANZAMOS: VÍDEO

        viewModelScope.launch {
            if(cSD1 != null && cSD2 != null)
                repository.insertRoll(cSD1.toByte(),cSD2.toByte())
        }

    }
}

class TwoDicesViewModelFactory(val caras: Int): ViewModelProvider.Factory{
    /**override fun <T : ViewModel> create(modelClass: Class<T>):T{
    @Suppress("UNCHECKED_CAST")
    if (modelClass.isAssignableFrom(TwoDicesViewModel::class.java))
    return TwoDicesViewModel(caras) as T
    throw IllegalArgumentException("ModelClass is not an instace of TwoDicesViewModel")
    CAMBIAMOS EL METODO PARA METERLE SOBRECARGADO, ver ejemplo.
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val app = checkNotNull(extras[APPLICATION_KEY]) as DiceRollerApp //CASTING PARA QUE q sea la no generica
        val repo = app.localRepo
        return TwoDicesViewModel(caras, repo) as T
    }//Nos lo podemos ahorrar con la inyección automática de dependencias.

}