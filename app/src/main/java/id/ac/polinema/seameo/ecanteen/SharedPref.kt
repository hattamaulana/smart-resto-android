/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/27/19 9:00 PM
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
    }

    var uid: String
        get() = sharedPreferences.getString(UID, "")!!
        set(value) = share { it.putString(UID, value) }

    private val share: (callback: (it: SharedPreferences.Editor)-> Unit) -> Unit = { callback ->
        with(sharedPreferences.edit()) {
            callback(this)
            apply()
        }
    }
}