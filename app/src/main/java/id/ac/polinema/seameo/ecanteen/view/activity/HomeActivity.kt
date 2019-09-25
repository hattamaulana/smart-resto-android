/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/25/19 1:36 PM
 */

package id.ac.polinema.seameo.ecanteen.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.view.utils.setWindow

class HomeActivity : AppCompatActivity() {
    val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set status bar transparent
        // And set content view
        setWindow(window)
        setContentView(R.layout.activity_home)

        val navHost = supportFragmentManager
                .findFragmentById(R.id.homeFragment) as NavHostFragment
        val navController = navHost.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val currentDest = destination.id
            Log.i(TAG, "Current Destination : ${resources.getResourceName(currentDest)}")

        }
    }
}
