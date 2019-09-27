/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/27/19 11:41 PM
 */

package id.ac.polinema.seameo.ecanteen

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.google.firebase.FirebaseApp

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        const val  TAG = "SEAMEO-KANTIN"

        // FireStoreRepository
        const val ITEM_COLLECTION = "items"
        const val TRANSACTION_COLLECTION = "transactions"

        // Realtime Database
        const val MAIN_REFERENCE = "ecanteen"
        const val ORDER_REFERENCE = "order"
        const val KITCHEN_QUEUE = "queue"
        const val CALL_WAITER_REFERENCE = "call_waiter"
    }
}
