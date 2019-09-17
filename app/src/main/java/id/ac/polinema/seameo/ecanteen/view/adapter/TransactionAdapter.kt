/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 7/2/19 4:31 PM
 */

package id.ac.polinema.seameo.ecanteen.view.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.ArrayList

import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.model.ItemModel

class TransactionAdapter(private val mListItem: ArrayList<ItemModel>?, context: Context) : RecyclerView.Adapter<TransactionAdapter.Holder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): Holder {
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.adapter_transaksi, viewGroup, false)

        return Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, i: Int) {
        val item = mListItem!![i]
        val payment = item.price * item.count

        holder.mName.text = item.name
        holder.mCount.text = "x " + item.count
        holder.mPrice.text = "Rp " + item.price
        holder.mPayment.text = "Rp $payment"
    }

    override fun getItemCount(): Int {
        return if (mListItem != null && mListItem.size > 0) {
            mListItem.size
        } else {
            0
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mName: TextView
        private val mCount: TextView
        private val mPrice: TextView
        private val mPayment: TextView

        init {

            mName = itemView.findViewById(R.id.txt_name_trnsc)
            mCount = itemView.findViewById(R.id.txt_count_trnsc)
            mPrice = itemView.findViewById(R.id.txt_price_trnsc)
            mPayment = itemView.findViewById(R.id.txt_payment_trnsc)
        }
    }
}
