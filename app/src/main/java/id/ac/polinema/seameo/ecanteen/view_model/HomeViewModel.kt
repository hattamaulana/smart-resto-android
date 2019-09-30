/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/25/19 10:14 AM
 */

package id.ac.polinema.seameo.ecanteen.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.polinema.seameo.ecanteen.App
import id.ac.polinema.seameo.ecanteen.SharedPref
import id.ac.polinema.seameo.ecanteen.model.StatusModel
import id.ac.polinema.seameo.ecanteen.repository.RealtimeDbRepository

class HomeViewModel(app: Application): AndroidViewModel(app) {
    private val sharedPref = SharedPref(app)
    private val statusRepo = RealtimeDbRepository(App.MAIN_REFERENCE)

    val statusLive = MutableLiveData<String>()

    init { statusRepo.child = listOf(App.STATUS_REF) }

    fun status() {
        statusRepo.get {
            statusLive.postValue("")

            for (data in it.children) {
                val status = data.getValue(StatusModel::class.java)

                if (status?.uid == sharedPref.uid) {
                    statusLive.postValue(status.status)
                } else {
                    statusLive.postValue("")
                }
            }
        }
    }
}