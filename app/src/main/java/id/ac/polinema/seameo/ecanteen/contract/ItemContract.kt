/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 10:45 AM
 */

package id.ac.polinema.seameo.ecanteen.contract

import com.google.firebase.firestore.QueryDocumentSnapshot

import java.util.ArrayList

import id.ac.polinema.seameo.ecanteen.model.ItemModel
import id.ac.polinema.seameo.ecanteen.presenter.BasePresenter
import id.ac.polinema.seameo.ecanteen.view.BaseView

class ItemContract {
    class Scanner {
        interface Presenter : BasePresenter {
            fun find(s: String, callback: Callback)
            fun save(it: ItemModel)
            fun remove()
        }

        interface Callback {
            fun onSuccess(q: QueryDocumentSnapshot)
            fun onFailed()
        }
    }

    class ScannerResult {
        interface Presenter : BasePresenter {
            fun show(callback: Callback)
        }

        interface Callback {
            fun setView(list: ArrayList<ItemModel>, id: String)
        }
    }

    interface View : BaseView
}
