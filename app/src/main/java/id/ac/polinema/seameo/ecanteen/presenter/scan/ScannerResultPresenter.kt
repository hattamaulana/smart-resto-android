/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 10:45 AM
 */

package id.ac.polinema.seameo.ecanteen.presenter.scan

import android.annotation.SuppressLint

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

import java.util.ArrayList

import id.ac.polinema.seameo.ecanteen.contract.ItemContract
import id.ac.polinema.seameo.ecanteen.model.ItemModel
import id.ac.polinema.seameo.ecanteen.presenter.Presenter

class ScannerResultPresenter : Presenter(), ItemContract.ScannerResult.Presenter {
    private val TAG = "SCANNER_RESULT_PRESENTER"

    override fun show(callback: ItemContract.ScannerResult.Callback) {
        super.getRealtimeDB(object : ValueEventListener {
            @SuppressLint("LongLogTag")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = ArrayList<ItemModel>()
                var key: String? = ""

                for (it in dataSnapshot.children) {
                    val item = it.getValue<ItemModel>(ItemModel::class.java)
                    item!!.key = it.key!!
                    list.add(item)
                    key = it.key
                }

                callback.setView(list, key!!)
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }
}
