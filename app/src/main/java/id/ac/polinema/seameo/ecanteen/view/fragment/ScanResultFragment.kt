/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 7/2/19 4:31 PM
 */

package id.ac.polinema.seameo.ecanteen.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

import java.util.ArrayList

import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.view.adapter.ScanResultAdapter
import id.ac.polinema.seameo.ecanteen.contract.ItemContract
import id.ac.polinema.seameo.ecanteen.model.ItemModel
import id.ac.polinema.seameo.ecanteen.presenter.scan.ScannerResultPresenter
import id.ac.polinema.seameo.ecanteen.view.activity.ScanActivity
import id.ac.polinema.seameo.ecanteen.contract.ItemContract.ScannerResult.Callback

class ScanResultFragment : Fragment(), ItemContract.View {
    private val TAG = "SCAN_RESULT_FRAGMENT"

    private val mListItem: ArrayList<ItemModel>? = null
    private var mPresenter: ScannerResultPresenter? = null
    private var mRecyclerView: RecyclerView? = null
    private var mLayoutManager: LinearLayoutManager? = null
    private var mAdapter: ScanResultAdapter? = null

    private val listItemCallback = object : Callback {
        override fun setView(list: ArrayList<ItemModel>, id: String) {
            if (list.size > 0) {
                mAdapter = ScanResultAdapter(list, context!!, id)
                mRecyclerView!!.layoutManager = mLayoutManager
                mRecyclerView!!.adapter = mAdapter
                mAdapter!!.notifyDataSetChanged()
            } else {
                try {
                    Toast.makeText(context, "Silahkan Lakukan Scanning Kembali", Toast.LENGTH_SHORT)
                            .show()

                    fragmentManager!!
                            .beginTransaction()
                            .replace(ScanActivity.container, ScannerFragment())
                            .commit()

                } catch (e: NullPointerException) {
                }

            }
        }
    }

    private val onButtonScanClicked = View.OnClickListener {
        ScanActivity.deleteData = false
        fragmentManager!!.beginTransaction()
                .replace(ScanActivity.container, ScannerFragment())
                .commit()
    }

    private val onButtonPayClicked = View.OnClickListener {
        ScanActivity.deleteData = false
        fragmentManager!!.beginTransaction()
                .replace(ScanActivity.container, TransactionFragment())
                .commit()
    }

    override fun initPresenter() {
        mPresenter = ScannerResultPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_scan_result, container, false)
        initPresenter()
        initView(v)

        return v
    }

    override fun onStart() {
        super.onStart()

        mPresenter!!.show(listItemCallback)
    }

    private fun initView(v: View) {
        mRecyclerView = v.findViewById(R.id.list_scan_result)
        mLayoutManager = LinearLayoutManager(context)
        mLayoutManager!!.orientation = LinearLayoutManager.VERTICAL

        val scan = v.findViewById<Button>(R.id.btn_add_scan)
        scan.setOnClickListener(onButtonScanClicked)

        val transaction = v.findViewById<Button>(R.id.btn_checkout)
        transaction.setOnClickListener(onButtonPayClicked)
    }
}
