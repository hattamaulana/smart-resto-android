/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/29/19 8:59 AM
 */

package id.ac.polinema.seameo.ecanteen.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import id.ac.polinema.seameo.ecanteen.App
import id.ac.polinema.seameo.ecanteen.SharedPref
import id.ac.polinema.seameo.ecanteen.model.CallingWaiterModel
import id.ac.polinema.seameo.ecanteen.model.MenuModel
import id.ac.polinema.seameo.ecanteen.model.OrderModel
import id.ac.polinema.seameo.ecanteen.repository.FireStoreRepository
import id.ac.polinema.seameo.ecanteen.repository.RealtimeDbRepository

class ScannerViewModel(app: Application) : AndroidViewModel(app) {
    private val TAG = this.javaClass.simpleName
    private val sharedPref = SharedPref(app)
    private val menuRepo = FireStoreRepository(App.ITEM_COLLECTION)
    private val orderRepo = RealtimeDbRepository(App.MAIN_REFERENCE)
    private val callWaiterRepo = RealtimeDbRepository(App.MAIN_REFERENCE)

    init {
        orderRepo.child = listOf(App.ORDER_REFERENCE, sharedPref.uid)
        callWaiterRepo.child = listOf(App.WAITER_REFERENCE, App.WAITER_HELPER_REFERENCE)
    }

    fun scanning(data: String) {
        menuRepo.get { document ->
            if (data == document.id) {
                Log.i(TAG, "Result : ${document.data}")

                saveToRealtime(document.id, document.toObject(MenuModel::class.java))
            }
        }
    }

    private fun saveToRealtime(id: String, data: MenuModel) {
        val params = OrderModel(id, data, 1, "")

         orderRepo.add(params.toMap())
    }

    fun callingWaiter(data: CallingWaiterModel) {
        data.uid = sharedPref.uid

        callWaiterRepo.add(data.toMap())
    }
}