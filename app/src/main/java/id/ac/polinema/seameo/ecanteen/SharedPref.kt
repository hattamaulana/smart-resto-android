/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/27/19 10:57 PM
 */

package id.ac.polinema.seameo.ecanteen

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class SharedPref (app: Application) {
    private val TAG = "SMART-RESTO"
    private val sharedPreferences = app.getSharedPreferences(TAG, Context.MODE_PRIVATE)

    companion object {
        const val UID = "UID"
        const val ON_QUEUE = "ON_QUEUE"
        const val CALLED_WAITER = "CALLED_WAITER"
    }

    var uid: String
        get() = sharedPreferences.getString(UID, "")!!
        set(value) = share { it.putString(UID, value) }

    var onQueue: Boolean
        get() = sharedPreferences.getBoolean(ON_QUEUE, false)
        set(value) = share { it.putBoolean(ON_QUEUE, value) }

    var calledWaiter: Boolean
        get() = sharedPreferences.getBoolean(CALLED_WAITER, false)
        set(value) = share { it.putBoolean(CALLED_WAITER, value) }

    private val share: (callback: (it: SharedPreferences.Editor)-> Unit) -> Unit = { callback ->
        with(sharedPreferences.edit()) {
            callback(this)
            apply()
        }
    }
}