/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/26/19 1:57 PM
 */

package id.ac.polinema.seameo.ecanteen.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RealtimeDbRepository(var _ref: String) {
    private val TAG = this.javaClass.simpleName
    private val database = FirebaseDatabase.getInstance()
    private var reference = database.reference

    var ref: String = _ref
        set(value) {
            reference = database.getReference(value)
            field = value }
    var child: List<String>? = null
        set(value) {
            value?.forEach {
                reference = database.reference.child(it) }
            field = value }

    fun add(data: Map<String, String>) {
        val key = reference.push().key
        val task = reference.push().setValue(data)

        execute(task) {

        }
    }

    fun remove(idKey: String) {
        val task = reference.child(idKey).removeValue()

        execute(task) {

        }
    }

    fun update(data: Map<String, Any>) {
        val task = reference.updateChildren(data)

        execute(task) {

        }
    }

    fun search(target: String) {
        reference.equalTo(target).addListenerForSingleValueEvent (object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.i(TAG, "TASK : CANCELED")
            }

            override fun onDataChange(p0: DataSnapshot) {
                Log.i(TAG, "TASK : DATA CHANGED")
            }
        })
    }

    fun get() {
        reference.addValueEventListener (object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.i(TAG, "TASK : CANCELED")
            }

            override fun onDataChange(p0: DataSnapshot) {
                Log.i(TAG, "TASK : DATA CHANGED")
            }
        })
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