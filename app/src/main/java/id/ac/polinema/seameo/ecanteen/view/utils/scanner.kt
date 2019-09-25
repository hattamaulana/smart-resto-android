/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/25/19 2:58 PM
 */

package id.ac.polinema.seameo.ecanteen.view.utils

import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator

fun scanning(fragment: Fragment) {
    IntentIntegrator.forSupportFragment(fragment)
            .setOrientationLocked(true)
            .setPrompt("Scann Barcode")
            .setOrientationLocked(false)
            .setCameraId(0)
            .setBarcodeImageEnabled(false)
            .initiateScan()
}