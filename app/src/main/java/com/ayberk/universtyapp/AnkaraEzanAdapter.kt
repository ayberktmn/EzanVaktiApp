package com.ayberk.universtyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.universtyapp.models.AnkaraEzanSaatiItem

class AnkaraEzanAdapter: RecyclerView.Adapter<AnkaraEzanAdapter.MyCustomHolder>() {

    var liveData : List<AnkaraEzanSaatiItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): AnkaraEzanAdapter.MyCustomHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.ezan_item,parent,false)
        return MyCustomHolder(view)
    }

    override fun onBindViewHolder(holder: AnkaraEzanAdapter.MyCustomHolder, position: Int) {
        holder.bind(liveData!!.get(position))
        holder.txtAylik.setOnClickListener {
            holder.view.findNavController().navigate(R.id.action_ankaraEzanFragment_to_ankaraAylikEzanFragment)
        }
        /* holder.btnezan.setOnClickListener {
             val action = SehirlerFragmentDirections.actionSehirlerFragmentToEzanSaatleriFragment(position)
             holder.btnezan.findNavController().navigate(action)
         } */

    }
    class MyCustomHolder(val view: View):
        RecyclerView.ViewHolder(view) {


        val txtsabah = view.findViewById<TextView>(R.id.txtSabah)
        val txtogle = view.findViewById<TextView>(R.id.txtOgle)
        val txtikindi= view.findViewById<TextView>(R.id.txtIkindi)
        val txtaksam = view.findViewById<TextView>(R.id.txtAksam)
        val txtyatsi = view.findViewById<TextView>(R.id.txtYatsi)
        val txttarih = view.findViewById<TextView>(R.id.txtdate)
        val txtAylik = view.findViewById<TextView>(R.id.txtAylik)
        val imagee = view.findViewById<ImageView>(R.id.imageView)
        val btnezan = view.findViewById<Button>(R.id.button)
        val url ="https://namazvakti.diyanet.gov.tr/images/i3.gif"


        fun bind(data: AnkaraEzanSaatiItem) {

            txtsabah.setText(data.Imsak)
            txtogle.setText(data.Ogle)
            txtikindi.setText(data.Ikindi)
            txtaksam.setText(data.Aksam)
            txtyatsi.setText(data.Yatsi)
            txttarih.setText(data.MiladiTarihUzun)

            /*  var into = Glide.with(imagee)
                  .asBitmap()
                  .load(url)
                  .into(imagee) */


        }
    }

    override fun getItemCount(): Int {

        if(liveData == null){
            return 0
        }
        else{
            return 1
        }
    }
    fun setLists(liveData: List<AnkaraEzanSaatiItem>){
        this.liveData = liveData
        notifyDataSetChanged()
    }
}