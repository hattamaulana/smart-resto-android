/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/25/19 2:59 PM
 */

package id.ac.polinema.seameo.ecanteen.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.zxing.integration.android.IntentIntegrator
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.R.layout.fragment_scanner
import id.ac.polinema.seameo.ecanteen.view.utils.scanning
import id.ac.polinema.seameo.ecanteen.view_model.ScannerViewModel

class ScannerFragment : Fragment() {
    private val TAG = this.javaClass.simpleName
    private val arg by lazy {
        arguments?.getString("scanFor")
    }

    companion object {
        const val ADD_MENU = "ADD_MENU"
        const val CALL_WAITER = "CALL_WAITER"
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(fragment_scanner, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i(TAG, "Arguments : ${arg}")

        scanning(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator
                .parseActivityResult(requestCode, resultCode, data)
        val viewModel = ViewModelProviders.of(this)
                .get(ScannerViewModel::class.java)

        if (result.contents == null) {
            Log.i(TAG, "Result : ${result.contents}")

            when (arg) {
                ADD_MENU -> {
                    findNavController(this).navigate(R.id.orderDest)
                }

                CALL_WAITER -> {
                    findNavController(this).navigate(R.id.statusDest)
                }
            }
        } else {
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        val alert = AlertDialog.Builder(context!!)
        alert.setTitle("Peringatan")
                .setMessage("Apakah Anda Ingin Keluar Dari Aplikasi ini ? ")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()
                    findNavController(this).popBackStack()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    scanning(this)
                    dialog.dismiss()
                }
                .create()
                .show()
    }
}