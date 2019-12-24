package com.example.koneksidatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RVAdapterFakultas (private  val  context: Context, private val
arrayList: ArrayList<fakultas>):
        RecyclerView.Adapter<RVAdapterFakultas.Holder>(){
    override fun onBindViewHolder(holder: Holder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    holder.view.label_kodefakultas.text
        arrayList?.get(position)?.kode_fakultas
        holder.view.label_namafakultas.text = "nama fakultas:" +
                "+arrayList?.get(posisition)?.nama_fakultas"
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        override fun getItem(): int = arrayList!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates // .

        return
        Holder (LayoutInflater.from(parent.context).inflate(R.layout.fakultas_list,parent,false))
    }

    class Holder (val view: View) : RecyclerView.ViewHolder(view)
}