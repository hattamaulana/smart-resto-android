/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/26/19 12:18 AM
 */

package id.ac.polinema.seameo.ecanteen.view.utils

import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator

fun scanning(fragment: Fragment, message: String = "Scan Barcode") {
    IntentIntegrator.forSupportFragment(fragment)
            .setOrientationLocked(true)
            .setPrompt(message)
            .setOrientationLocked(false)
            .setCameraId(0)
            .setBarcodeImageEnabled(false)
            .initiateScan()
}