/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/27/19 11:31 PM
 */

package id.ac.polinema.seameo.ecanteen.view.utils

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import id.ac.polinema.seameo.ecanteen.R

fun setWindow(window: Window) {
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = Color.TRANSPARENT
}

interface AlertDialogCallback {
    fun positiveButton()
    fun negativeButton()
}

fun alertDialog(
        context: Context, inflater: LayoutInflater, ttle: String, msg: String,
        callback: () -> Unit
) {
    val TAG = "alertDialog()"
    val dialog = AlertDialog.Builder(context)
    val view = inflater.inflate(R.layout.dialog_alert_one_button, null)
    val dialogCreated by lazy { dialog.create() }

    val title = view.findViewById<TextView>(R.id.dialogTitle)
    val message = view.findViewById<TextView>(R.id.dialogBody)
    val positiveBtn = view.findViewById<Button>(R.id.btnPositive)

    title.text = ttle
    message.text = msg
    positiveBtn.setOnClickListener {
        Log.d(TAG, "CLICKED")
        callback()
        dialogCreated.dismiss()
    }

    dialog.setView(view)
    dialog.setCancelable(false)
    dialogCreated.show()
}

fun alertDialog(
        context: Context, inflater: LayoutInflater, restId: Int, msg: String,
        callback: AlertDialogCallback
) {
    val TAG = "alertDialog()"
    val dialog = AlertDialog.Builder(context)
    val view = inflater.inflate(restId, null)
    val dialogCreated by lazy { dialog.create() }
    val message = view.findViewById<TextView>(R.id.dialogMessage)
    val positiveBtn = view.findViewById<Button>(R.id.btnPositive)
    val negativeBtn = view.findViewById<Button>(R.id.btnNegative)

    message.text = msg
    positiveBtn.setOnClickListener {
        Log.d(TAG, "CLICKED")
        callback.positiveButton()
        dialogCreated.dismiss()
    }

    negativeBtn.setOnClickListener {
        Log.d(TAG, "CLICKED")

        callback.negativeButton()
        dialogCreated.dismiss()
    }

    dialog.setView(view)
    dialog.setCancelable(false)
    dialogCreated.show()
}

fun alertDialog(
        context: Context, inflater: LayoutInflater, ttle: String,
        callback: (it: String) -> Unit
) {
    val TAG = "alertDialog()"
    val dialog = AlertDialog.Builder(context)
    val view = inflater.inflate(R.layout.dialog_add_data, null)
    val dialogCreated by lazy { dialog.create() }
    val title = view.findViewById<TextView>(R.id.dialogTitle)
    val positiveBtn = view.findViewById<Button>(R.id.btnPositive)
    val negativeBtn = view.findViewById<Button>(R.id.btnNegative)
    val note = view.findViewById<EditText>(R.id.edtNote)

    title.text = ttle
    positiveBtn.setOnClickListener {
        Log.d(TAG, "CLICKED")

        callback(note.text.toString())
        dialogCreated.dismiss()
    }

    negativeBtn.setOnClickListener {
        Log.d(TAG, "CLICKED: Dialog Dismiss")

        dialogCreated.dismiss()
    }

    dialog.setView(view)
    dialog.setCancelable(false)
    dialogCreated.show()
}