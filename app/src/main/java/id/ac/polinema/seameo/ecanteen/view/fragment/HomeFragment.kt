/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/19/19 11:02 PM
 */

package id.ac.polinema.seameo.ecanteen.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.view.activity.ScanActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrayOf(btnHelper, btnScann).forEach {
            it.setOnClickListener {
                startActivity(Intent(context, ScanActivity::class.java))
            }
        }
    }
}