/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/26/19 10:33 PM
 */

package id.ac.polinema.seameo.ecanteen.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.view.adapter.OrderAdapter
import id.ac.polinema.seameo.ecanteen.view_model.OrderViewModel
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_order, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(this)
                .get(OrderViewModel::class.java)
        val adapter = OrderAdapter(this)

        listScanResult.layoutManager = LinearLayoutManager(context)
        listScanResult.adapter = adapter

        // Get Data
        viewModel.addMenu(adapter)

        btnAddMenu.setOnClickListener {
            findNavController(this).popBackStack()
            /* The End btnAddMenu On Click Listener */ }
        btnCheckout.setOnClickListener {
            findNavController(this).navigate(R.id.toCheckoutDest)
            /* The End btnCheckout On Click Listener */}

        activity?.onBackPressedDispatcher
                ?.addCallback(this, object: OnBackPressedCallback(true){
                    override fun handleOnBackPressed() {
                        // Handle Back Pressed
                        Toast.makeText(context, "BACK PRESSED", Toast.LENGTH_SHORT)
                                .show()
                        findNavController(this@OrderFragment)
                                .popBackStack(R.id.homeDest, true)
                    }})
    }
}