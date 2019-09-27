/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/27/19 11:38 PM
 */

package id.ac.polinema.seameo.ecanteen.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.R.layout.adapter_order
import id.ac.polinema.seameo.ecanteen.model.OrderModel
import id.ac.polinema.seameo.ecanteen.view.utils.alertDialog
import id.ac.polinema.seameo.ecanteen.view_model.OrderViewModel

class OrderAdapter(private val fragment: Fragment) : RecyclerView.Adapter<OrderAdapter.Holder>() {
    var listData: List<OrderModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder (
            LayoutInflater.from(fragment.context).inflate(adapter_order, parent, false) )

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val viewModel = ViewModelProviders.of(fragment)
                .get(OrderViewModel::class.java)
        val order = listData[position]
        val menu = order.menu

        holder.name.text = menu?.name
        holder.price.text = "Rp. ${menu?.price}"
        holder.count.text = "${order.count}"

        if (! order.note.isNullOrEmpty()) {
            holder.cardBody.visibility = View.VISIBLE
            holder.note.text = order.note
            holder.addNote.text = "EDIT CATATAN"
        } else {
            holder.cardBody.visibility = View.GONE
            holder.addNote.text = "TAMBAH CATATAN"
        }

        fun mathematic(subtract: Boolean): Int {
            var count = (holder.count.text as String).toInt()

            count = if (subtract) if (count > 1) count - 1
                                  else count
                    else count + 1

            holder.count.text = "$count"

            return count
        }

        holder.addNote.setOnClickListener {
            val change = order
            alertDialog(fragment.context!!, fragment.layoutInflater, "CATATAN") {
                change.note = it

                viewModel.updateData(order.key, change.toMap())
            }
            /* End of addNote onClick Listener */ }
        holder.btnRemove.setOnClickListener {
            viewModel.removeMenu(order.key)
            /* End of btnRemove onClick Listener */ }
        holder.btnSubtract.setOnClickListener {
            val change = order
            val count = mathematic(true)

            change.count = count
            viewModel.updateData(order.key, change.toMap())
            /* End of btnSubtract onClick Listener */ }
        holder.btnPlus.setOnClickListener {
            val change = order
            val count = mathematic(false)

            change.count = count
            viewModel.updateData(order.key, change.toMap())
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