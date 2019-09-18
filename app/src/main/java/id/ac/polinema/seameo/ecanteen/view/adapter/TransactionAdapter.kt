/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 7/2/19 4:31 PM
 */

package id.ac.polinema.seameo.ecanteen.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.model.ItemModel
import java.util.*

class TransactionAdapter(private val mListItem: ArrayList<ItemModel>?) : RecyclerView.Adapter<TransactionAdapter.Holder>() {

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
        val mName: TextView = itemView.findViewById(R.id.txt_name_trnsc)
        val mCount: TextView = itemView.findViewById(R.id.txt_count_trnsc)
        val mPrice: TextView = itemView.findViewById(R.id.txt_price_trnsc)
        val mPayment: TextView = itemView.findViewById(R.id.txt_payment_trnsc)
    }
}
