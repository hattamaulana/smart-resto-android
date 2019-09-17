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
import android.widget.ImageView
import android.widget.TextView

import com.google.firebase.database.FirebaseDatabase

import java.util.ArrayList

import id.ac.polinema.seameo.ecanteen.App
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.contract.ListResultContract
import id.ac.polinema.seameo.ecanteen.model.ItemModel
import id.ac.polinema.seameo.ecanteen.presenter.scan.ListResultPresenter

class ScanResultAdapter(val mListItem: ArrayList<ItemModel>?, val mContext: Context, val mKey: String)
    : RecyclerView.Adapter<ScanResultAdapter.Holder>(), ListResultContract.View {
    private var mPresenter: ListResultPresenter? = null

    override fun initPresenter() {
        mPresenter = ListResultPresenter()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): Holder {
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.adapter_scan_result, viewGroup, false)

        return Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, i: Int) {
        val item = mListItem!![i]

        holder.txtName.text = item.name
        holder.txtCount.text = item.count.toString()
        holder.btnIncrement.setOnClickListener(btnIncrementClicked(item, holder))
        holder.btnDecrement.setOnClickListener(btnDecrementClicked(item, holder))
        holder.btnDelete.setOnClickListener(btnDeleteClicked(item))
    }

    override fun getItemCount(): Int {
        return if (mListItem == null && mListItem!!.size == 0)
            0
        else
            mListItem.size
    }

    private fun btnIncrementClicked(item: ItemModel, holder: Holder): View.OnClickListener {
        return View.OnClickListener {
            val x = Integer.valueOf(holder.txtCount.text.toString()) + 1
            item.count = x

            FirebaseDatabase
                    .getInstance()
                    .getReference(App.MAIN_REFERENCE)
                    .child(App.ORDER_REFERENCE).child(item.key)
                    .updateChildren(item.toMap(item))
        }
    }

    private fun btnDecrementClicked(item: ItemModel, holder: Holder): View.OnClickListener {
        return View.OnClickListener {
            val x = Integer.valueOf(holder.txtCount.text.toString())
            if (x > 1) {
                item.count = x - 1

                FirebaseDatabase
                        .getInstance()
                        .getReference(App.MAIN_REFERENCE)
                        .child(App.ORDER_REFERENCE).child(item.key)
                        .updateChildren(item.toMap(item))
            }
        }
    }

    private fun btnDeleteClicked(item: ItemModel): View.OnClickListener {
        return View.OnClickListener {
            FirebaseDatabase
                    .getInstance()
                    .getReference(App.MAIN_REFERENCE)
                    .child(App.ORDER_REFERENCE).child(item.key)
                    .removeValue()
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txt_item_name)
        val txtCount: TextView = itemView.findViewById(R.id.txt_item_count)
        val btnIncrement: ImageView = itemView.findViewById(R.id.btn_increment)
        val btnDecrement: ImageView = itemView.findViewById(R.id.btn_decrement)
        val btnDelete: ImageView = itemView.findViewById(R.id.btn_delete)
    }
}
