/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/24/19 3:51 PM
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
        val TAG = "SEAMEO-KANTIN"

        // Firestore
        val ITEM_COLLECTION = "items"
        val TRANSACTION_COLLECTION = "transactions"

        // Realtime Database
        val MAIN_REFERENCE = "ecanteen"
        val ORDER_REFERENCE = "order"
        val KITCHEN_QUEUE = "queue"
    }
}
