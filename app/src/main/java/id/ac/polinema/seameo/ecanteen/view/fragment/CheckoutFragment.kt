/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/27/19 10:52 AM
 */

package id.ac.polinema.seameo.ecanteen.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.view.utils.isEmpty
import kotlinx.android.synthetic.main.fragment_transaction.*

class CheckoutFragment : Fragment() {
    private val TAG = this.javaClass.simpleName
    private val argNominal by lazy {
        arguments?.getInt("nominal") }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_transaction, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "Total Pembayaran : Rp $argNominal")
        edtNonimal.hint = "Total Pembayaran : Rp $argNominal"

        btnCheckout.setOnClickListener {
            if (!isEmpty(edtName) && !isEmpty(edtNonimal)) {
                val name = edtName.text
                val nominal = edtNonimal.text

                Navigation.findNavController(view)
                        .popBackStack(R.id.homeDest, false)
            } else {
                Toast.makeText(context, "Pastikan Input Sesuai", Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }
}