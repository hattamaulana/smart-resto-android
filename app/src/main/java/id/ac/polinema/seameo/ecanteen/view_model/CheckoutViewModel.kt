/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/27/19 10:35 PM
 */

package id.ac.polinema.seameo.ecanteen.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import id.ac.polinema.seameo.ecanteen.App
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.SharedPref
import id.ac.polinema.seameo.ecanteen.model.TransactionModel
import id.ac.polinema.seameo.ecanteen.repository.FireStoreRepository
import id.ac.polinema.seameo.ecanteen.repository.RealtimeDbRepository

class CheckoutViewModel(val app: Application): AndroidViewModel(app) {
    private val TAG = this.javaClass.simpleName
    private val sharedPref = SharedPref(app)
    private val orderRepo = RealtimeDbRepository(App.MAIN_REFERENCE)
    private val queueRepo = RealtimeDbRepository(App.MAIN_REFERENCE)
    private val statusRepo = RealtimeDbRepository(App.MAIN_REFERENCE)
    private val transaction = FireStoreRepository(App.TRANSACTION_COLLECTION)

    init {
        orderRepo.child = listOf(App.ORDER_REFERENCE)
        queueRepo.child = listOf(App.KITCHEN_QUEUE)
        statusRepo.child = listOf(App.STATUS_REF)
    }

    fun pay(args: TransactionModel) {
        val uid = sharedPref.uid

        Log.i(TAG, "UID : $uid")
        Log.i(TAG, "Size : ${args.orders?.size}")

        orderRepo.remove(sharedPref.uid)
        queueRepo.add(args.toMap())
        transaction.add(args.toMap())

        statusRepo.add(mapOf<String, Any?>(
                App.STATUS_REF to app.resources.getString(R.string.statusQueue),
                "uid" to uid
        ))
    }
}