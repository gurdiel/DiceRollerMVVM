package com.mpd.pmdm.dicerollerconstraintlayout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Clase que modela un dado con un número de caras configurable
 */
class Dice(private val numSides: Int) {

    //Guardamos en que estado se encuentra cada instancia del dado.
    //para prevenir modificaciones indeseadas hacemos esto.
    //propiedad Observable.
    private val _currentSide = MutableLiveData<Int>(0)
    val currentSide: LiveData<Int> = _currentSide

    //Creamos una función adicional que inicialice al dado para dar un valor válido.
    init {
        roll()
    }
    fun roll() {
        _currentSide.value = (1..numSides).random()
    }
}