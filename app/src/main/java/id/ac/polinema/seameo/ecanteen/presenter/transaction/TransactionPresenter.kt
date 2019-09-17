/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 12:14 PM
 */

package id.ac.polinema.seameo.ecanteen.presenter.transaction

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.QuerySnapshot

import java.util.ArrayList
import java.util.Date
import java.util.HashMap

import id.ac.polinema.seameo.ecanteen.App
import id.ac.polinema.seameo.ecanteen.contract.ItemContract
import id.ac.polinema.seameo.ecanteen.contract.TransactionContract
import id.ac.polinema.seameo.ecanteen.model.ItemModel
import id.ac.polinema.seameo.ecanteen.model.TransactionModel
import id.ac.polinema.seameo.ecanteen.presenter.Presenter
import id.ac.polinema.seameo.ecanteen.view.activity.ScanActivity

class TransactionPresenter : Presenter(), TransactionContract.Presenter {
    private val TAG = "TRANSACTION_PRESENTER"

    override fun all(callback: TransactionContract.Callback) {
        super.getFirestore(App.TRANSACTION_COLLECTION, transactionCallback(callback))
    }

    override fun get(callback: ItemContract.ScannerResult.Callback) {
        super.getRealtimeDB(getItem(callback))
    }

    override fun save(ob: TransactionModel) {
        val params = HashMap<String, Any>()

        params[TransactionModel.NAME] = ob.name
        params[TransactionModel.DATE_TIME] = Timestamp(Date())
        params[TransactionModel.ITEMS] = ob.items
        params[TransactionModel.MONEY] = ob.money
        params[TransactionModel.PAYMENT] = ob.payment
        params[TransactionModel.CASHBACK] = ob.cashback

        super.storeFirestore(params)
    }

    override fun onAttach(ft: FragmentTransaction, fg: Fragment) {
        var ft = ft
        ft = ft.replace(ScanActivity.container, fg)

        super.onAttach(ft, fg)
    }

    private fun transactionCallback(callback: TransactionContract.Callback): OnCompleteListener<*> {
        return OnCompleteListener<QuerySnapshot> { task ->
            if (task.isSuccessful) {
                callback.run(task.result)
            }
        }
    }

    private fun getItem(callback: ItemContract.ScannerResult.Callback): ValueEventListener {
        return object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = ArrayList<ItemModel>()
                var key: String? = ""

                for (it in dataSnapshot.children) {
                    val item = it.getValue<ItemModel>(ItemModel::class.java)
                    item!!.key = it.key
                    list.add(item)
                    key = it.key
                }

                callback.setView(list, key)
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
    }

}
