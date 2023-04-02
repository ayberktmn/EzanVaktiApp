package com.ayberk.universtyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.universtyapp.models.*

class SehirAdapter: RecyclerView.Adapter<SehirAdapter.MyCustomHolder>() {

    var liveData : List<SehirItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): MyCustomHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.sehir_item,parent,false)
        return MyCustomHolder(view)
    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {

        holder.bind(liveData!!.get(position))

    }
    class MyCustomHolder(val view: View):
        RecyclerView.ViewHolder(view) {


        val btnulke = view.findViewById<Button>(R.id.button)


        fun bind(data: SehirItem) {
           // txttitlepop.text = data.SehirAdi
            btnulke.setText(data.SehirAdi)

            if(data.SehirID == "555" ){

                btnulke.setOnClickListener {
                    view.findNavController().navigate(R.id.ezanSaatleriFragment)
                }
            }
            if(data.SehirID == "506" ){

                btnulke.setOnClickListener {
                    view.findNavController().navigate(R.id.ankaraEzanFragment)
                }
            }

        }
    }

    override fun getItemCount(): Int {

        if(liveData == null){
            return 0
        }
        else{
            return liveData!!.size
        }
    }
    fun setLists(liveData: List<SehirItem>){
        this.liveData = liveData
        notifyDataSetChanged()
    }
}