package com.example.androidmvvmcoroutines.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter<T>(
    private val context: Context,
    private val resourceId: Int,
    private var items: List<T>?,
    private var setItemView : (v: View?, item: T, position: Int)->Unit
) : RecyclerView.Adapter<BaseAdapter<T>.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        return ViewHolder(view)
    }


    fun updateItem(position: Int, newItem: T) {
        notifyItemChanged(position, newItem)
    }

    fun setList(newlist: ArrayList<T>?) {
        items = newlist
        notifyDataSetChanged()
    }

    fun getItems(): List<T>? {
        return items
    }

    override fun getItemCount(): Int {
        return if (items != null) {
            items!!.size
        } else 0
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(
        view
    )

/*    fun removeAt(position: Int) {
        items?.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items!!.size)
    }*/

    override  fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items!![position]
        setItemView(holder.view,item,position)
    }




}