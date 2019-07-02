/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 10:45 AM
 */

package id.ac.polinema.seameo.ecanteen.presenter.scan;

import com.google.firebase.database.DatabaseReference;

import id.ac.polinema.seameo.ecanteen.App;
import id.ac.polinema.seameo.ecanteen.presenter.Presenter;
import id.ac.polinema.seameo.ecanteen.contract.ListResultContract;

public class ListResultPresenter extends Presenter implements ListResultContract.Presenter {
    public DatabaseReference realtimeDB;

    public ListResultPresenter() {
        super();

        this.realtimeDB = super.mRealtimeDb;
    }

    @Override
    public void update(String id, String data) {
        if (data != null) {
            mRealtimeDb.child(App.ORDER_REFERENCE).child(id).child("count")
                      .setValue(data);
        }
    }
}
