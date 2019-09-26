/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/26/19 9:48 PM
 */

package id.ac.polinema.seameo.ecanteen.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.R.layout.adapter_order
import id.ac.polinema.seameo.ecanteen.model.OrderModel

class OrderAdapter(private val context: Context) : RecyclerView.Adapter<OrderAdapter.Holder>() {
    var list: List<OrderModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder (
            LayoutInflater.from(context).inflate(adapter_order, parent, false) )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val order = list[position]
        val menu = order.menu

        holder.name.text = menu?.name
        holder.price.text = "Rp. ${menu?.price}"

        if (! order.note.isNullOrEmpty()) {
            holder.cardBody.visibility = View.VISIBLE
            holder.note.text = order.note
        } else {
            holder.cardBody.visibility = View.GONE
        }

        fun mathematic(add: Boolean) {
            var count = (holder.count.text as String).toInt()

            if (count > 0) {
                count = if (add) count + 1 else count - 1
                holder.count.text = "$count"
            }
        }

        holder.addNote.setOnClickListener {
            /* End of addNote onClick Listener */ }
        holder.btnRemove.setOnClickListener {
            /* End of btnRemove onClick Listener */ }
        holder.btnSubtract.setOnClickListener {
            mathematic(false)
            /* End of btnSubtract onClick Listener */ }
        holder.btnPlus.setOnClickListener {
            mathematic(true)
            /* End of btnPlus onClick Listener */ }
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val btnRemove = view.findViewById<ImageView>(R.id.icRemove)
        val btnSubtract = view.findViewById<ImageView>(R.id.icSubtract)
        val btnPlus = view.findViewById<ImageView>(R.id.icPlus)
        val name = view.findViewById<TextView>(R.id.txtName)
        val price = view.findViewById<TextView>(R.id.txtPrice)
        val count = view.findViewById<TextView>(R.id.txtCount)
        val note = view.findViewById<TextView>(R.id.txtNote)
        val addNote = view.findViewById<TextView>(R.id.txtAddNote)
        val cardBody = view.findViewById<ConstraintLayout>(R.id.cardBody)
    }
}