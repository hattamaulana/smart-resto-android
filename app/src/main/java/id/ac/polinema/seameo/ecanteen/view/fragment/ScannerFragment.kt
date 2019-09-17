/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/25/19 9:51 AM
 */

package id.ac.polinema.seameo.ecanteen.view.fragment

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

import id.ac.polinema.seameo.ecanteen.contract.ItemContract
import id.ac.polinema.seameo.ecanteen.model.ItemModel
import id.ac.polinema.seameo.ecanteen.presenter.scan.ScannerPresenter
import id.ac.polinema.seameo.ecanteen.view.activity.ScanActivity

class ScannerFragment : Fragment(), ItemContract.View {
    private val TAG = "SCANNER_FRAGMENT"
    private var mPresenter: ScannerPresenter? = null
    private var mProggressDialog: ProgressDialog? = null
    private var mAlertDialog: AlertDialog? = null
    private var mBack = 0

    private val callbackSave = object : ItemContract.Scanner.Callback {
        override fun onSuccess(q: QueryDocumentSnapshot) {
            mProggressDialog!!.dismiss()
            val item = q.toObject(ItemModel::class.java)
            item.id = q.id
            item.count = 1

            mPresenter!!.save(item)

            fragmentManager!!.beginTransaction()
                    .replace(ScanActivity.container, ScanResultFragment())
                    .commitAllowingStateLoss()
        }

        override fun onFailed() {
            mBack = 0
            Toast.makeText(context, "QR-Code Tidak dikenali", Toast.LENGTH_SHORT).show()
            onStart()
        }
    }

    override fun initPresenter() {
        mPresenter = ScannerPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initPresenter()
        initView()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        when (mBack) {
            0 -> IntentIntegrator.forSupportFragment(this)
                    .setOrientationLocked(true)
                    .setPrompt("Scann Barcode Sekarang")
                    .setOrientationLocked(false)
                    .setCameraId(0)
                    .setBarcodeImageEnabled(false)
                    .initiateScan()
            1 -> mAlertDialog!!.show()
            2 -> mProggressDialog!!.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val res = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        Log.i(TAG, "onActivityResult: " + res!!.contents)

        if (res != null) {
            if (res.contents == null)
                mBack = 1
            else {
                mBack = 2
                processResult(res.contents)
            }
        } else
            super.onActivityResult(requestCode, resultCode, data)
    }

    private fun processResult(res: String) {
        mPresenter!!.find(res, callbackSave)
    }

    private fun initView() {
        mAlertDialog = AlertDialog.Builder(context)
                .setTitle("Peringatan")
                .setMessage("Jika Anda Kembali Data yang Sudah di Scan Akan Hilang.")
                .setPositiveButton("Biarkan", positiveButton())
                .setNegativeButton("Batalkan", negativeButton())
                .create()

        mProggressDialog = ProgressDialog(context)
        mProggressDialog!!.setMessage("Loading")
    }

    private fun positiveButton(): DialogInterface.OnClickListener {
        return DialogInterface.OnClickListener { dialog, which -> activity!!.finish() }
    }

    private fun negativeButton(): DialogInterface.OnClickListener {
        return DialogInterface.OnClickListener { dialog, which ->
            mAlertDialog!!.dismiss()
            mBack = 0
            onStart()
        }
    }
}