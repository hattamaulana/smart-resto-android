/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/24/19 12:14 PM
 */

package id.ac.polinema.seameo.ecanteen.view.activity

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.contract.ItemContract
import id.ac.polinema.seameo.ecanteen.presenter.scan.ScannerPresenter
import id.ac.polinema.seameo.ecanteen.view.fragment.ScannerFragment

class ScanActivity : AppCompatActivity(), ItemContract.View {

    private var mPresenter: ScannerPresenter? = null

    override fun initPresenter() {
        mPresenter = ScannerPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        initPresenter()
        initView()
    }

    override fun onStop() {
        if (deleteData) {
            mPresenter!!.remove()
        } else {
            deleteData = true
        }

        super.onStop()
    }

    override fun onBackPressed() {
        val alert = AlertDialog.Builder(this)

        alert.setTitle("Peringatan")
                .setMessage("Apakah Anda Ingin Keluar Dari Aplikasi ini ? ")
                .setPositiveButton("Ya") { dialog, which ->
                    dialog.dismiss()
                    finish()
                }
                .setNegativeButton("Tidak") { dialog, which -> dialog.dismiss() }
                .create()
                .show()
    }

    /**
     * @method initView, used to initiation all component view
     */
    private fun initView() {
        // Set Default View as Scanner
        mPresenter!!.onAttach(supportFragmentManager.beginTransaction(), ScannerFragment())
    }

    companion object {
        val container = R.id.frame_scan_activity
        var deleteData = true
    }
}
