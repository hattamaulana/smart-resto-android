/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 10:45 AM
 */

package id.ac.polinema.seameo.ecanteen.contract

import id.ac.polinema.seameo.ecanteen.presenter.BasePresenter
import id.ac.polinema.seameo.ecanteen.view.BaseView

class ListResultContract {
    interface Presenter : BasePresenter {
        fun update(id: String, data: String)
    }

    interface View : BaseView
}
