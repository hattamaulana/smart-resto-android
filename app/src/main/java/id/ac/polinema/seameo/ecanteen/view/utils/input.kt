/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/25/19 10:07 AM
 */

package id.ac.polinema.seameo.ecanteen.view.utils

import android.widget.EditText

fun isEmpty(view: EditText): Boolean {
    return view.text.toString().isEmpty()
}