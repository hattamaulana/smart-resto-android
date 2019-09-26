/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/26/19 12:28 PM
 */

package id.ac.polinema.seameo.ecanteen.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import id.ac.polinema.seameo.ecanteen.App
import id.ac.polinema.seameo.ecanteen.repository.FireStoreRepository
import id.ac.polinema.seameo.ecanteen.repository.RealtimeDbRepository

class OrderViewModel(app: Application): AndroidViewModel(app) {
    private val firestoreRepo = FireStoreRepository(App.ITEM_COLLECTION)
    private val realtimeDbRepo = RealtimeDbRepository(App.MAIN_REFERENCE)

    init {
        realtimeDbRepo.child = listOf(App.ORDER_REFERENCE)
    }

    fun addMenu() {}

    fun removeMenu() {}

    fun addQuantity() {}

    fun subtractQuantity() {}

    fun addNote() {}

    fun checkout() {}
}