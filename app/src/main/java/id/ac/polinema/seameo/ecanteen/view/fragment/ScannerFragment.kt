/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/26/19 1:22 AM
 */

package id.ac.polinema.seameo.ecanteen.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.zxing.integration.android.IntentIntegrator
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.R.layout.fragment_scanner
import id.ac.polinema.seameo.ecanteen.view.utils.AlertDialogCallback
import id.ac.polinema.seameo.ecanteen.view.utils.alertDialog
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

        scanning(this, when(arg){
            CALL_WAITER -> "SCAN BARCODE MEJA YANG DIGUNAKAN"
            ADD_MENU -> "SCAN BARCODE MENU YANG DIPILIH"
            else -> "SCAN BARCODE"
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator
                .parseActivityResult(requestCode, resultCode, data)
        val viewModel = ViewModelProviders.of(this)
                .get(ScannerViewModel::class.java)
        val barcode = result.contents

        Log.i(TAG, "Result : $barcode")

        when (arg) {
            ADD_MENU -> if (barcode == null) {
                    findNavController(this).navigate(R.id.toOrderDest)
            } else {
                showingAlertDialog("Semua menu yang di order akan di hapus.")
            }

            CALL_WAITER -> if (barcode == null) {
                    findNavController(this).popBackStack()
            } else {
                showingAlertDialog("Batal memanggil waiter")
            }
        }
    }

    private fun showingAlertDialog(message: String) {
        alertDialog(context!!, layoutInflater, R.layout.dialog_alert_two_button, message,
                object : AlertDialogCallback {
                    override fun positiveButton() {
                        findNavController(this@ScannerFragment)
                                .popBackStack()
                    }

                    override fun negativeButton() {
                        scanning(this@ScannerFragment)
                    }
                }
        )
    }
}