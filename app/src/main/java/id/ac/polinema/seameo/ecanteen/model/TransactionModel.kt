/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/27/19 10:08 PM
 */

package id.ac.polinema.seameo.ecanteen.model

data class TransactionModel (
        var uid: String = "",
        var name: String = "",
        var dateTime: String = "",
        var orders: List<OrderModel>? = null,
        var noTable: Int = -1,
        var money: Int = 0,
        var cashback: Int = 0,
        var nominal: Int = 0
) {
    fun toMap() = mapOf<String, Any?>(
            "uid" to uid,
            "name" to name,
            "dateTime" to dateTime,
            "orders" to orders,
            "noTable" to noTable,
            "money" to money,
            "cassback" to cashback,
            "money" to nominal
    )
}
