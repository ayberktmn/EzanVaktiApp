package com.ayberk.universtyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.universtyapp.models.AnkaraEzanSaatiItem
import com.ayberk.universtyapp.models.ManisaEzanSaatiItem
import com.bumptech.glide.Glide

class AnkaraaylikezanAdapter: RecyclerView.Adapter<AnkaraaylikezanAdapter.aylikezan>() {

    var liveData : List<AnkaraEzanSaatiItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): AnkaraaylikezanAdapter.aylikezan {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.aylikezan_item,parent,false)
        return aylikezan(view)
    }

    override fun onBindViewHolder(holder: AnkaraaylikezanAdapter.aylikezan, position: Int) {


        holder.bind(liveData!!.get(position))
        /* holder.btnezan.setOnClickListener {
             val action = SehirlerFragmentDirections.actionSehirlerFragmentToEzanSaatleriFragment(position)
             holder.btnezan.findNavController().navigate(action)
         } */

    }
    class aylikezan(val view: View):
        RecyclerView.ViewHolder(view) {


        val txtsabah = view.findViewById<TextView>(R.id.txtSabah)
        val txtogle = view.findViewById<TextView>(R.id.txtOgle)
        val txtikindi= view.findViewById<TextView>(R.id.txtIkindi)
        val txtaksam = view.findViewById<TextView>(R.id.txtAksam)
        val txtyatsi = view.findViewById<TextView>(R.id.txtYatsi)
        val txttarih = view.findViewById<TextView>(R.id.txtdate)
        val imageay = view.findViewById<ImageView>(R.id.imageView2)

        val btnezan = view.findViewById<Button>(R.id.button)
        val url ="https://namazvakti.diyanet.gov.tr/images/i3.gif"


        fun bind(data: AnkaraEzanSaatiItem) {

            txtsabah.setText(data.Imsak)
            txtogle.setText(data.Ogle)
            txtikindi.setText(data.Ikindi)
            txtaksam.setText(data.Aksam)
            txtyatsi.setText(data.Yatsi)
            txttarih.setText(data.MiladiTarihUzun)

             var into = Glide.with(imageay)
                  .asGif()
                  .load(url)
                  .into(imageay)

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
    fun setLists(liveData: List<AnkaraEzanSaatiItem>){
        this.liveData = liveData
        notifyDataSetChanged()
    }
}