/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/27/19 11:37 AM
 */

package id.ac.polinema.seameo.ecanteen.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import java.util.*

class FireStoreRepository(_collection: String) {
    var collection: String = _collection
        set(value) {
            database = FirebaseFirestore.getInstance().collection(value) }

    private val TAG = this.javaClass.simpleName
    private var database = FirebaseFirestore.getInstance().collection(collection)

    fun add(data: Map<String, Any?>) {
        val task = database.add(data)

        execute(task) {

        }
    }

    fun remove(doc: String) {
        val task = database.document(doc).delete()

        execute(task) {

        }
    }

    fun update(doc: String, data: Map<String, Any>) {
        val task = database.document(doc).update(data)

        execute(task) {

        }
    }

    fun search(key: String, value: Objects) {
        val task = database.whereEqualTo(key, value).get()
        var result: Boolean = false

        execute(task) {
            for (document in it) {
                Log.d(TAG, "${document.id} => ${document.data}")
            }
        }
    }

    fun get(callback: (it: QueryDocumentSnapshot)-> Unit) {
        val task = database.get()

        execute(task) {
            for (document in it) {
                Log.d(TAG, "${document.id} => ${document.data}")
                callback(document)
            }
        }
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