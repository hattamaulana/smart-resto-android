/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 9/26/19 9:36 PM
 */

package id.ac.polinema.seameo.ecanteen.model

import javax.annotation.meta.Exclusive

data class OrderModel (
        var idMenu: String? = null,
        var menu: MenuModel? = null,
        var count: Int? = null,
        var note: String? = null
) {
    @Exclusive
    fun toMap(data: OrderModel): Map<String, Any?> = mapOf(
            "idMenu" to data.idMenu,
            "menu" to data.menu,
            "count" to data.count,
            "note" to data.note
    )

    fun toMap(): Map<String, Any?> = mapOf(
            "idMenu" to idMenu,
            "menu" to menu,
            "count" to count,
            "note" to note
    )
}