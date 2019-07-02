/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 10:45 AM
 */

package id.ac.polinema.seameo.ecanteen.presenter.scan;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import id.ac.polinema.seameo.ecanteen.App;
import id.ac.polinema.seameo.ecanteen.contract.ItemContract;
import id.ac.polinema.seameo.ecanteen.model.ItemModel;
import id.ac.polinema.seameo.ecanteen.presenter.Presenter;
import id.ac.polinema.seameo.ecanteen.view.activity.ScanActivity;

public class ScannerPresenter extends Presenter implements ItemContract.Scanner.Presenter {
    private static final String TAG = "SCANNER_PRESENTER";

    public ScannerPresenter() {
        super();
    }

    @Override
    public void find(String s, ItemContract.Scanner.Callback callback) {
        super.getFirestore(App.ITEM_COLLECTION, getItem(s, callback));
    }

    @Override
    public void save(ItemModel it) {
        super.storeRealtimeDB(it);
    }

    @Override
    public void remove() {
        super.removeRealtimeDB(App.ORDER_REFERENCE);
    }

    @Override
    public void onAttach(FragmentTransaction ft, Fragment fg) {
        ft = ft.replace(ScanActivity.container, fg);

        super.onAttach(ft, fg);
    }

    private OnCompleteListener getItem(final String id, final ItemContract.Scanner.Callback callback) {
        return new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    boolean found = true;
                    for (QueryDocumentSnapshot q : task.getResult()) {
                        if (q.getId().equals(id)) {
                            found = false;
                            callback.onSuccess(q);
                        }
                    }

                    if (found) {
                        callback.onFailed();
                    }
                }
            }
        };
    }
}
