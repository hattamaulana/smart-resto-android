/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/29/19 9:40 AM
 */

package id.ac.polinema.seameo.ecanteen.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.polinema.seameo.ecanteen.App
import id.ac.polinema.seameo.ecanteen.SharedPref
import id.ac.polinema.seameo.ecanteen.model.OrderModel
import id.ac.polinema.seameo.ecanteen.repository.FireStoreRepository
import id.ac.polinema.seameo.ecanteen.repository.RealtimeDbRepository
import id.ac.polinema.seameo.ecanteen.view.adapter.OrderAdapter

class OrderViewModel(app: Application): AndroidViewModel(app) {
    private val TAG = this.javaClass.simpleName
    private val sharedPref = SharedPref(app)
    private val firestoreRepo = FireStoreRepository(App.ITEM_COLLECTION)
    private val realtimeDbRepo = RealtimeDbRepository(App.MAIN_REFERENCE)

    companion object {
        var LIST: List<OrderModel>? = null
    }

    var nominal = MutableLiveData<Int>()

    init {
        realtimeDbRepo.child = listOf(App.ORDER_REFERENCE, sharedPref.uid)

        Log.i(TAG, "UID : ${sharedPref.uid}")
    }

    fun addMenu(adapter: OrderAdapter) {
        realtimeDbRepo.get {
            Log.i(TAG, "addMenu() : ${it.value}")

            var nominal = 0
            val listData = ArrayList<OrderModel>()

            if (it.value == null) this.nominal.postValue(0)

            for (data in it.children) {
                val order = data.getValue(OrderModel::class.java)
                nominal += order?.menu?.price!! * order.count!!

                this.nominal.postValue(nominal)
                order.key = data.key!!

                listData.add(order)
            }

            LIST = listData
            adapter.listData = listData
            adapter.notifyDataSetChanged()
        }
    }

    fun updateData(id: String, data: Map<String, Any?>) {
        realtimeDbRepo.update(id, data)
    }

    fun removeMenu(id: String = "") {
        realtimeDbRepo.remove(id)
    }
}