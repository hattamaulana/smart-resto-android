/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/26/19 1:59 PM
 */

package id.ac.polinema.seameo.ecanteen.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import id.ac.polinema.seameo.ecanteen.App
import id.ac.polinema.seameo.ecanteen.repository.FireStoreRepository
import id.ac.polinema.seameo.ecanteen.repository.RealtimeDbRepository

class CheckoutViewModel(app: Application): AndroidViewModel(app) {
    private val firestoreRepo = FireStoreRepository(App.TRANSACTION_COLLECTION)
    private val realtimeDbRepo = RealtimeDbRepository(App.KITCHEN_QUEUE)

    fun pay(i: Map<String, Any>) {}
}