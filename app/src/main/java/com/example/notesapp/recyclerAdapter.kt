package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class recyclerAdapter(val listener:click) :RecyclerView.Adapter<recyclerAdapter.viewHolder>() {
    var list =ArrayList<Notes>()
    inner class viewHolder(view: View):RecyclerView.ViewHolder(view){
        val text= view.findViewById<TextView>(R.id.text)
       

        val date= view.findViewById<TextView>(R.id.date)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
     val view=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
       val myView=viewHolder(view)
    view.setOnClickListener {
        listener.itemClick(list[myView.adapterPosition])
        //listener.date(list[myView.adapterPosition])
    }
        return myView
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val myNotes=list[position]
        holder.text.setText(myNotes.text)
        holder.date.setText(myNotes.date)
    }

    override fun getItemCount(): Int {
  return  list.size
    }

    fun refresh(noteList :List<Notes>){
        list.clear()
        list.addAll(noteList)
        notifyDataSetChanged()

    }
    interface click{
        fun itemClick(note:Notes)
      //  fun date(note:Notes)
    }
}