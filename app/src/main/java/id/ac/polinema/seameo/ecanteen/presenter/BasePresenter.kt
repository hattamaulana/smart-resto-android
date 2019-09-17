/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 10:45 AM
 */

package id.ac.polinema.seameo.ecanteen.presenter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.ValueEventListener

import java.util.HashMap

interface BasePresenter {
    fun getFirestore(s: String, listener: OnCompleteListener<*>)
    fun storeFirestore(args: HashMap<String, Any>)

    fun removeRealtimeDB(s: String)
    fun getRealtimeDB(callback: ValueEventListener)
    fun storeRealtimeDB(ob: Any)

    fun onAttach(ft: FragmentTransaction, fg: Fragment)
}
