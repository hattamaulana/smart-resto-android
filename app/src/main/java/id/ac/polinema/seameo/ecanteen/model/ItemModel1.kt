/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 12:14 PM
 */

package id.ac.polinema.seameo.ecanteen.model

import java.util.HashMap

import javax.annotation.meta.Exclusive

data class ItemModel(
        var key: String = "",
        var id: String = "",
        var name: String = "",
        var imgUri: String = "",
        var price: Int = 0,
        var count: Int = 0
) {
    @Exclusive
    fun toMap(item: ItemModel): Map<String, Any> = mapOf(
                ID to item.id,
                NAME to item.name,
                IMAGE_URI to item.imgUri,
                PRICE to item.price,
                COUNT to item.count
        )

    companion object {
        var KEY = "key"
        var ID = "id"
        var NAME = "name"
        var IMAGE_URI = "imgUri"
        var PRICE = "price"
        var COUNT = "count"
    }
}
