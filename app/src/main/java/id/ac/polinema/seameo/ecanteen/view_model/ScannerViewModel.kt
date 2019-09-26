/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/26/19 9:48 PM
 */

package id.ac.polinema.seameo.ecanteen.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import id.ac.polinema.seameo.ecanteen.App
import id.ac.polinema.seameo.ecanteen.model.MenuModel
import id.ac.polinema.seameo.ecanteen.model.OrderModel
import id.ac.polinema.seameo.ecanteen.repository.FireStoreRepository
import id.ac.polinema.seameo.ecanteen.repository.RealtimeDbRepository

class ScannerViewModel(app: Application) : AndroidViewModel(app) {
    private val TAG = this.javaClass.simpleName
    private val firestore = FireStoreRepository(App.ITEM_COLLECTION)
    private val realtimeRepo = RealtimeDbRepository(App.MAIN_REFERENCE)

    init {
        realtimeRepo.child = listOf(App.ORDER_REFERENCE)
    }

    fun scanning(data: String) {
        firestore.get { document ->
            if (data == document.id) {
                Log.i(TAG, "Result : ${document.data}")

                saveToRealtime(document.id, document.toObject(MenuModel::class.java))
            }
        }
    }

    private fun saveToRealtime(id: String, data: MenuModel) {
        val params = OrderModel(id, data, 1, "")

         realtimeRepo.add(params.toMap())
    }
}