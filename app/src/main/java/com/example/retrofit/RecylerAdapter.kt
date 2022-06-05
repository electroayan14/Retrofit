package com.example.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

open class RecylerAdapter(val context: Context, val userlist:List<DataItemModel>):RecyclerView.Adapter<RecylerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecylerAdapter.ViewHolder {
        val itemView= LayoutInflater.from(context).inflate(R.layout.row_items,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecylerAdapter.ViewHolder, position: Int) {
       holder.userId.text= userlist[position].userId.toString()
        holder.title.text=userlist[position].title.toString()
    }

    override fun getItemCount(): Int {
     return userlist.size
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var userId: TextView
        var title:  TextView
        init {
            userId=itemView.findViewById(R.id.txt_userId)
            title=itemView.findViewById(R.id.txt_title)
        }
    }

}