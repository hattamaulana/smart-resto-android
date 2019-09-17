/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 7/2/19 4:31 PM
 */

package id.ac.polinema.seameo.ecanteen.view.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import com.google.firebase.database.FirebaseDatabase

import java.util.ArrayList

import id.ac.polinema.seameo.ecanteen.App
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.contract.ItemContract
import id.ac.polinema.seameo.ecanteen.contract.TransactionContract
import id.ac.polinema.seameo.ecanteen.model.ItemModel
import id.ac.polinema.seameo.ecanteen.model.KitchenQueueModel
import id.ac.polinema.seameo.ecanteen.model.TransactionModel
import id.ac.polinema.seameo.ecanteen.presenter.transaction.TransactionPresenter
import id.ac.polinema.seameo.ecanteen.view.activity.ScanActivity
import id.ac.polinema.seameo.ecanteen.view.adapter.TransactionAdapter
import id.ac.polinema.seameo.ecanteen.contract.ItemContract.ScannerResult.Callback

class TransactionFragment : Fragment(), TransactionContract.View {

    private var mPresenter: TransactionPresenter? = null
    private var mItems: ArrayList<String>? = null
    private var mListResult: ArrayList<ItemModel>? = null
    private var mName: EditText? = null
    private var mMoney: EditText? = null
    private var mCountPay: TextView? = null
    private var mCashback: Int = 0
    private var mPayment: Int = 0
    private var mRecyclerView: RecyclerView? = null
    private var mLayoutManager: LinearLayoutManager? = null
    private var mAdapter: TransactionAdapter? = null

    private val itemCallback = object : Callback {
        override fun setView(list: ArrayList<ItemModel>, id: String) {
            if (list.size > 0) {
                mListResult = list
                mAdapter = TransactionAdapter(list, context!!)
                mRecyclerView!!.layoutManager = mLayoutManager
                mRecyclerView!!.adapter = mAdapter
                mAdapter!!.notifyDataSetChanged()

                for (item in list) {
                    mPayment += item.price * item.count

                    for (i in 1..item.count)
                        mItems!!.add(item.id)
                }

                mCountPay!!.text = "Rp $mPayment"
            }
        }
    }

    private val storeTransaction = View.OnClickListener {
        var errorMessage = ""
        val isEmptyForName = mName!!.text.toString() == ""
        val isEmptyForMoney = mMoney!!.text.toString() == ""

        if (!(isEmptyForName && isEmptyForMoney)) {
            val transaction = TransactionModel()
            val money = Integer.valueOf(mMoney!!.text.toString())
            mCashback = money - mPayment

            transaction.name = mName!!.text.toString()
            transaction.money = money
            transaction.items = mItems
            transaction.payment = mPayment
            transaction.cashback = mCashback
            mPresenter!!.save(transaction)

            val menus = ArrayList<KitchenQueueModel.Menu>()

            for (it in mListResult!!)
                menus.add(KitchenQueueModel.Menu(it.name, it.count))

            val queue = KitchenQueueModel(
                    mName!!.text.toString(),
                    menus
            )

            FirebaseDatabase.getInstance()
                    .getReference(App.MAIN_REFERENCE).child(App.KITCHEN_QUEUE)
                    .push().setValue(queue)

            val dialog = AlertDialog.Builder(context)
                    .setPositiveButton("Ok", positiveButtonCallback)

            if (mCashback > 0)
                dialog.setMessage("Kembalian $mCashback").create().show()
            else
                dialog.setMessage("Terimakasih Telah Membayar dengan Uang Pas").create().show()
        } else {
            if (isEmptyForName)
                errorMessage = "Nama"

            if (isEmptyForMoney)
                errorMessage += if (errorMessage == "") "Nominal Uang" else " & Nominal Uang"
        }

        if (errorMessage != "") {
            errorMessage = "Text Field $errorMessage Tidak Boleh Kosong."
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private val positiveButtonCallback = DialogInterface.OnClickListener { dialog, which ->
        dialog.dismiss()

        ScanActivity.deleteData = true
        fragmentManager!!.beginTransaction()
                .replace(ScanActivity.container, ScannerFragment())
                .commit()
    }

    override fun initPresenter() {
        mPresenter = TransactionPresenter()
    }

    override fun onStart() {
        super.onStart()

        mItems = ArrayList()
        mPayment = 0
        mCashback = 0

        mPresenter!!.get(itemCallback)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_transaction, container, false)

        initPresenter()
        initView(v)

        return v
    }

    @SuppressLint("WrongConstant")
    fun initView(v: View) {
        mName = v.findViewById(R.id.edtxt_name)
        mMoney = v.findViewById(R.id.edtxt_money)
        mCountPay = v.findViewById(R.id.txt_payment)
        mRecyclerView = v.findViewById(R.id.list_transaction)

        mLayoutManager = LinearLayoutManager(context)
        mLayoutManager!!.orientation = LinearLayout.VERTICAL

        val btnStoreTransaction = v.findViewById<Button>(R.id.btn_pay)
        btnStoreTransaction.setOnClickListener(storeTransaction)
    }

    companion object {
        private val TAG = "TRANSACTION_FRAGMENT"
    }
}
