/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/25/19 3:35 PM
 */

package id.ac.polinema.seameo.ecanteen.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.view.utils.isEmpty
import kotlinx.android.synthetic.main.fragment_transaction.*

class CheckoutFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_transaction, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCheckout.setOnClickListener {
            if (isEmpty(edtName) && isEmpty(edtNonimal)) {
                val name = edtName.text
                val nominal = edtNonimal.text

                findNavController(this).navigate(R.id.fromCheckoutToHome)
            } else {
                Toast.makeText(context, "Pastikan Input Sesuai", Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }
}