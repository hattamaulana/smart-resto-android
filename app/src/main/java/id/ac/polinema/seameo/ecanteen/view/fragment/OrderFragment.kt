/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/29/19 9:49 AM
 */

package id.ac.polinema.seameo.ecanteen.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.view.adapter.OrderAdapter
import id.ac.polinema.seameo.ecanteen.view.utils.AlertDialogCallback
import id.ac.polinema.seameo.ecanteen.view.utils.alertDialog
import id.ac.polinema.seameo.ecanteen.view_model.OrderViewModel
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : Fragment() {
    private val TAG = this.javaClass.simpleName
    var amount = -1

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_order, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noTable = arguments?.getInt("noTable")
        Log.i(TAG, "No Meja : $noTable")

        val viewModel = ViewModelProviders.of(this)
                .get(OrderViewModel::class.java)
        val adapter = OrderAdapter(this)

        listScanResult.layoutManager = LinearLayoutManager(context)
        listScanResult.adapter = adapter

        // Get Data
        viewModel.addMenu(adapter)

        viewModel.nominal.observe(this, Observer<Int> {
            if (it > 0) {
                amount += it
                txtTotal.text = "Rp $it"
                proggres.visibility = View.GONE
            } else {
                txtTotal.text = ""
                proggres.visibility = View.VISIBLE
            }
        })

        btnAddMenu.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
            /* The End btnAddMenu On Click Listener */ }
        btnCheckout.setOnClickListener {
            if (txtTotal.text.toString() != "") {
                val nominalString = txtTotal.text.toString().substring(3)
                val nominalInt = nominalString.trim().toInt()
                Log.d(TAG, "Nominal : $nominalInt")

                val params = Bundle()
                params.putInt("noTable", noTable!!)
                params.putInt("nominal", nominalInt)

                Navigation.findNavController(view)
                        .navigate(R.id.toCheckoutDest, params)
            }
            /* The End btnCheckout On Click Listener */}

        activity?.onBackPressedDispatcher
                ?.addCallback(this, object: OnBackPressedCallback(true){
                    override fun handleOnBackPressed() {
                        // Handle Back Pressed
                        val msg = "Jika Anda kembali semua order akan di hapus."

                        alertDialog(context!!, layoutInflater, msg, object : AlertDialogCallback {
                            override fun positiveButton() {
                                viewModel.removeMenu()
                                Navigation.findNavController(view)
                                        .popBackStack(R.id.homeDest, false)
                            }

                            override fun negativeButton() {}
                        })
                    }})
    }
}