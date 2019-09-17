/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 10:45 AM
 */

package id.ac.polinema.seameo.ecanteen.presenter.scan

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

import id.ac.polinema.seameo.ecanteen.App
import id.ac.polinema.seameo.ecanteen.contract.ItemContract
import id.ac.polinema.seameo.ecanteen.model.ItemModel
import id.ac.polinema.seameo.ecanteen.presenter.Presenter
import id.ac.polinema.seameo.ecanteen.view.activity.ScanActivity

class ScannerPresenter : Presenter(), ItemContract.Scanner.Presenter {

    override fun find(s: String, callback: ItemContract.Scanner.Callback) {
        super.getFirestore(App.ITEM_COLLECTION, getItem(s, callback))
    }

    override fun save(it: ItemModel) {
        super.storeRealtimeDB(it)
    }

    override fun remove() {
        super.removeRealtimeDB(App.ORDER_REFERENCE)
    }

    override fun onAttach(ft: FragmentTransaction, fg: Fragment) {
        var ft = ft
        ft = ft.replace(ScanActivity.container, fg)

        super.onAttach(ft, fg)
    }

    private fun getItem(id: String, callback: ItemContract.Scanner.Callback): OnCompleteListener<*> {
        return OnCompleteListener<QuerySnapshot> { task ->
            if (task.isSuccessful) {
                var found = true
                for (q in task.result!!) {
                    if (q.id == id) {
                        found = false
                        callback.onSuccess(q)
                    }
                }

                if (found) {
                    callback.onFailed()
                }
            }
        }
    }

    companion object {
        private val TAG = "SCANNER_PRESENTER"
    }
}
