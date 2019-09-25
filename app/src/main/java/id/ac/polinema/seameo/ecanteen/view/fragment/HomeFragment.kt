/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/25/19 2:19 PM
 */

package id.ac.polinema.seameo.ecanteen.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.R.id.toScanner
import id.ac.polinema.seameo.ecanteen.view_model.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(this)
                .get(HomeViewModel::class.java)

        fun navigate(it: LinearLayout, data: String) {
            it.setOnClickListener {
                val param = Bundle()
                param.putString("scanFor", data)

                NavHostFragment.findNavController(this)
                        .navigate(toScanner, param)
            }
        }

        navigate(btnScann, ScannerFragment.ADD_MENU)
        navigate(btnHelper, ScannerFragment.CALL_WAITER)
    }
}