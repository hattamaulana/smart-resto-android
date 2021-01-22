/*
 * Copyright (c) 2021.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 1/22/21 11:52 AM
 */

package id.ac.polinema.seameo.ecanteen.model

data class CallingWaiterModel(var uid: String = "", var noTable: Int = -1, var note: String = "") {
    fun toMap(): Map<String, Any?> = mapOf(
            "uid" to uid,
            "noTable" to noTable,
            "note" to note
    )
}