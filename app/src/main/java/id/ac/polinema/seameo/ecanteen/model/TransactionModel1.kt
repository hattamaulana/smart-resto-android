/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 12:14 PM
 */

package id.ac.polinema.seameo.ecanteen.model

import java.util.ArrayList

class TransactionModel {

    var name: String? = null
    var dateTime: String? = null
    var items: ArrayList<String>? = null
    var money: Int = 0
    var cashback: Int = 0
    var payment: Int = 0

    companion object {
        var NAME = "name"
        var DATE_TIME = "date_time"
        var ITEMS = "items"
        var MONEY = "money"
        var CASHBACK = "cashback"
        var PAYMENT = "payment"
    }
}
