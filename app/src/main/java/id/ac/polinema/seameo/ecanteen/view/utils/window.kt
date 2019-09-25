/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/25/19 4:50 PM
 */

package id.ac.polinema.seameo.ecanteen.view.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.Window
import android.view.WindowManager

fun setWindow(window: Window) {
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = Color.TRANSPARENT
}


fun windowAlert(
        context: Context,
        title: String = "Alert", message: String = "",
        action: (it: AlertDialog.Builder) -> AlertDialog.Builder
) {
    var alert = AlertDialog.Builder(context)

    action(alert)
    alert.setTitle(title)
    alert.setMessage(message)
    alert.create()
    alert.show()
}