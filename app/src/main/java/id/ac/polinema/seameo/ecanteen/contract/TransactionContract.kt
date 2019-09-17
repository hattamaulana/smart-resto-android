/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 10:45 AM
 */

package id.ac.polinema.seameo.ecanteen.contract

import com.google.firebase.firestore.QuerySnapshot

import id.ac.polinema.seameo.ecanteen.model.TransactionModel
import id.ac.polinema.seameo.ecanteen.presenter.BasePresenter
import id.ac.polinema.seameo.ecanteen.view.BaseView

class TransactionContract {
    interface Presenter : BasePresenter {
        fun all(callback: Callback)
        operator fun get(callback: ItemContract.ScannerResult.Callback)
        fun save(ob: TransactionModel)
    }

    interface Callback {
        fun run(q: QuerySnapshot)
    }

    interface View : BaseView
}
