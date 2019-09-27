/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/27/19 10:57 PM
 */

package id.ac.polinema.seameo.ecanteen.model

data class CallingWaiterModel (var uid: String = "", var noTable: Int = -1, var note: String = "") {
    fun toMap(): Map<String, Any?> = mapOf(
            "uid" to uid,
            "noTable" to noTable,
            "note" to note
    )
}