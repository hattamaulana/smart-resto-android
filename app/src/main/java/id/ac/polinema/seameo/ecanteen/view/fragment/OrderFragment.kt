/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/26/19 9:10 AM
 */

package id.ac.polinema.seameo.ecanteen.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import id.ac.polinema.seameo.ecanteen.R
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_order, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCheckout.setOnClickListener {
            findNavController(this).navigate(R.id.toCheckoutDest)
        }

        activity?.onBackPressedDispatcher
                ?.addCallback(this, object: OnBackPressedCallback(true){
                    override fun handleOnBackPressed() {
                        // Handle Back Pressed
                        Toast.makeText(context, "BACK PRESSED", Toast.LENGTH_SHORT)
                                .show()
                    }})
    }
}