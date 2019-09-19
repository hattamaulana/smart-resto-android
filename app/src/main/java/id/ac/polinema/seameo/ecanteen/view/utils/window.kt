/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/19/19 10:31 PM
 */

package id.ac.polinema.seameo.ecanteen.view.utils

import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun setWindow(window: Window) {
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = Color.TRANSPARENT
}