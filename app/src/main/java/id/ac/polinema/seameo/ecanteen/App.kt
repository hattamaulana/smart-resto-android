/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/27/19 7:23 PM
 */

package id.ac.polinema.seameo.ecanteen

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

import com.google.firebase.FirebaseApp
import java.util.*

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
        val UID = UUID.randomUUID().toString()
        val TAG = "SEAMEO-KANTIN"

        // FireStoreRepository
        val ITEM_COLLECTION = "items"
        val TRANSACTION_COLLECTION = "transactions"

        // Realtime Database
        val MAIN_REFERENCE = "ecanteen"
        val ORDER_REFERENCE = "order"
        val KITCHEN_QUEUE = "queue"
    }
}
