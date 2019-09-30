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
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
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

        viewModel.status()

        viewModel.statusLive.observe(this, Observer {
            if (it.isNotEmpty()) {
                statusWrapper.setBackgroundColor(resources.getColor(R.color.mainColor))
                txtStatus.setBackgroundColor(resources.getColor(R.color.mainColor))
                txtStatus.setTextColor(resources.getColor(R.color.textPrimary))
                txtStatus.text = it

                if (it == resources.getString(R.string.statusQueue)) {
                    statusIlustration.setImageResource(R.drawable.cooking)
                } else if (it == resources.getString(R.string.statusReady)) {
                    statusIlustration.setImageResource(R.drawable.food_ready)
                }
            } else {
                statusIlustration.setImageResource(R.drawable.resto)
                statusWrapper.setBackgroundColor(resources.getColor(R.color.bgStatus))
                txtStatus.setBackgroundColor(resources.getColor(R.color.bgStatus))
                txtStatus.setTextColor(resources.getColor(R.color.mainColor))

                txtStatus.text = "Welcome"
            }
        })

        fun navigate(it: View, data: String) {
            it.setOnClickListener {
                val param = Bundle()
                param.putString("scanFor", data)

                findNavController(view).navigate(toScanner, param)
            }
        }

        navigate(btnScann, ScannerFragment.ADD_MENU)
        navigate(btnHelper, ScannerFragment.CALL_WAITER)
    }
}