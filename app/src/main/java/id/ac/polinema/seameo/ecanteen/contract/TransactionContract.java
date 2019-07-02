/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 10:45 AM
 */

package id.ac.polinema.seameo.ecanteen.contract;

import com.google.firebase.firestore.QuerySnapshot;

import id.ac.polinema.seameo.ecanteen.model.TransactionModel;
import id.ac.polinema.seameo.ecanteen.presenter.BasePresenter;
import id.ac.polinema.seameo.ecanteen.view.BaseView;

public class TransactionContract {
    public interface Presenter extends BasePresenter {
        void all(Callback callback);
        void get(ItemContract.ScannerResult.Callback callback);
        void save(TransactionModel ob);
    }

    public interface Callback {
        void run(QuerySnapshot q);
    }

    public interface View extends BaseView { }
}
