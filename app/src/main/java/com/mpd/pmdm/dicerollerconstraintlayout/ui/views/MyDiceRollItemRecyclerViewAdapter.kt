package com.mpd.pmdm.dicerollerconstraintlayout.ui.views

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.DiceRolls
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentDiceRollItemBinding
import java.text.SimpleDateFormat
import java.util.Date

class MyDiceRollItemRecyclerViewAdapter(
    private var diceRollsList: List<DiceRolls>
) : RecyclerView.Adapter<MyDiceRollItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentDiceRollItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = diceRollsList[position]
        holder.bindData(item)
    }

    override fun getItemCount(): Int = diceRollsList.size

    inner class ViewHolder(val binding: FragmentDiceRollItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(item:DiceRolls){
            binding.idRollItem.text = item.id.toString()
            binding.dice1ResultItem.text = item.dice1Result.toString()
            binding.dice2ResultItem.text = item.dice2Result.toString()
            //timeStamp pasamos milisegundos.
            binding.rollDateItem.text = SimpleDateFormat("dd/MM/y h:mm a").format(Date(item.timestamp))
        }
    }

    fun updateList(newList: List<DiceRolls>){
        diceRollsList = newList
        notifyDataSetChanged() //No es eficiente porque actualiza toda la lista aunque solo entre un elemento.
    }
}