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

class KitchenQueueModel(var name: String?, var menus: ArrayList<Menu>?) {
    var key: String? = null
    class Menu(var name: String?, var count: Int)
}
