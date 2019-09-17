/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 10:45 AM
 */

package id.ac.polinema.seameo.ecanteen.presenter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import id.ac.polinema.seameo.ecanteen.App
import java.util.*

open class Presenter : BasePresenter {
    protected var mFirestore: FirebaseFirestore
    protected var mRealtimeDb: DatabaseReference

    private val TAG = "PRESENTER"
    private val result: Any? = null

    /**
     * @method Constructore
     * Initiation Firebase Firestore and RealtimeDB
     */
    init {
        // Initiation Firebase Firestore
        mFirestore = FirebaseFirestore.getInstance()

        // initiation Firebase Realtime Database
        mRealtimeDb = FirebaseDatabase.getInstance().getReference(App.MAIN_REFERENCE)
    }

    override fun getFirestore(s: String, listener: OnCompleteListener<*>) {
        mFirestore.collection(s).get().addOnCompleteListener(listener as OnCompleteListener<QuerySnapshot>)
    }

    override fun getRealtimeDB(callback: ValueEventListener) {
        mRealtimeDb.child(App.ORDER_REFERENCE).addValueEventListener(callback)
    }

    override fun storeFirestore(args: HashMap<String, Any>) {
        mFirestore.collection(App.TRANSACTION_COLLECTION)
                .add(args)
                .addOnSuccessListener { Log.i(TAG, "onSuccess: Success Save Data") }
    }

    override fun storeRealtimeDB(ob: Any) {
        mRealtimeDb.child(App.ORDER_REFERENCE).push().setValue(ob)
    }

    override fun removeRealtimeDB(s: String) {
        mRealtimeDb.child(s).setValue("")
    }

    override fun onAttach(ft: FragmentTransaction, fg: Fragment) {
        ft.commit()
    }
}
