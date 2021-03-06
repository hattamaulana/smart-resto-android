/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/29/19 9:37 AM
 */

package id.ac.polinema.seameo.ecanteen.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RealtimeDbRepository(_ref: String) {
    private val TAG = this.javaClass.simpleName
    private val database = FirebaseDatabase.getInstance()
    private var reference = database.reference

    var child: List<String>? = null
        set(value) {
            value?.forEach {
                Log.i(TAG, "child: $it")
                reference = reference.child(it) }
                field = value }

    init {
        reference = database.getReference(_ref)
    }

    fun getOnce(callback: (p: DataSnapshot) -> Unit) {
        reference.addListenerForSingleValueEvent (object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.i(TAG, "TASK : CANCELED")
            }

            override fun onDataChange(p0: DataSnapshot) {
                Log.i(TAG, "TASK : DATA CHANGED")
            }
        })
    }

    fun get(callback: (p: DataSnapshot)-> Unit): ValueEventListener {
        return reference.addValueEventListener (object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.i(TAG, "TASK : CANCELED")
            }

            override fun onDataChange(p0: DataSnapshot) {
                Log.i(TAG, "TASK : DATA CHANGED")
                callback(p0)
            }
        })
    }

    fun add(data: Map<String, Any?>) {
        val task = reference.push().setValue(data)

        execute(task) {
            Log.i(TAG, "Berhasil Menambahkan Data Status")
        }
    }

    fun remove(idKey: String = "") {
        val task = if (idKey == "")
            reference.removeValue()
        else
            reference.child(idKey).removeValue()

        execute(task) { Log.i(TAG, "remove() : DONE") }
    }

    fun update(child: String, data: Map<String, Any?>) {
        val task = reference.child(child).updateChildren(data)

        execute(task) {}
    }

    private fun <T> execute(task: Task<T>, callback: (it: T) -> Unit) {
        task.addOnSuccessListener {
            Log.i(TAG, "TASK : SUCCESS")
            callback(it as T) }
                .addOnFailureListener {
                    Log.i(TAG, "TASK : FAILURE")
                    Log.e(TAG, "FAILURE LISTENER : ", it) }
                .addOnCompleteListener {
                    Log.i(TAG, "TASK : COMPLETE") }
    }
}