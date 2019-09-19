/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/19/19 10:52 PM
 */

package id.ac.polinema.seameo.ecanteen.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.polinema.seameo.ecanteen.R
import id.ac.polinema.seameo.ecanteen.view.utils.setWindow

class HomeActivity : AppCompatActivity() {
    override fun toString(): String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setWindow(window)
        setContentView(R.layout.activity_home)
    }
}
