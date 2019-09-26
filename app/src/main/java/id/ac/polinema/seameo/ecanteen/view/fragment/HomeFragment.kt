/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/26/19 1:57 PM
 */

package id.ac.polinema.seameo.ecanteen.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.R.id.toScanner
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fun navigate(it: View, data: String) {
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