/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/27/19 10:35 PM
 */

package id.ac.polinema.seameo.ecanteen.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.SharedPref
import id.ac.polinema.seameo.ecanteen.model.TransactionModel
import id.ac.polinema.seameo.ecanteen.view.utils.alertDialog
import id.ac.polinema.seameo.ecanteen.view.utils.isEmpty
import id.ac.polinema.seameo.ecanteen.view_model.CheckoutViewModel
import id.ac.polinema.seameo.ecanteen.view_model.OrderViewModel
import kotlinx.android.synthetic.main.fragment_transaction.*
import java.text.SimpleDateFormat
import java.util.*

class CheckoutFragment : Fragment() {
    private val TAG = this.javaClass.simpleName
    private val argNominal by lazy {
        arguments?.getInt("nominal") }
    private val viewModel by lazy { ViewModelProviders.of(this)
            .get(CheckoutViewModel::class.java) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_transaction, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = SharedPref(activity!!.application)

        Log.d(TAG, "Total Pembayaran : Rp $argNominal")
        edtNonimal.hint = "Total Pembayaran : Rp $argNominal"

        btnCheckout.setOnClickListener {
            if (!isEmpty(edtName) && !isEmpty(edtNonimal)) {
                val name = edtName.text.toString()
                val money = edtNonimal.text.toString()

                if (money.toInt() >= argNominal!!) {
                    val dateTime = "${SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())}"
                    val cashback = money.toInt() - argNominal!!
                    val param = TransactionModel(
                            sharedPref.uid, name, dateTime,
                            OrderViewModel.LIST, 1,
                            money.toInt(), cashback, argNominal!!)

                    viewModel.pay(param)
                    showingAlertDialog("Terimakasih Sudah Menggunakan Aplikasi ini")
                }
            } else {
                Toast.makeText(context, "Pastikan Input Sesuai", Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }

    private fun showingAlertDialog(message: String) {
        alertDialog(context!!, layoutInflater, "INFORMASI",  message) {
            Navigation.findNavController(view!!)
                    .popBackStack(R.id.homeDest, false)
        }
    }
}