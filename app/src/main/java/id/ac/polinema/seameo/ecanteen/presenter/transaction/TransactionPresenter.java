/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 12:14 PM
 */

package id.ac.polinema.seameo.ecanteen.presenter.transaction;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import id.ac.polinema.seameo.ecanteen.App;
import id.ac.polinema.seameo.ecanteen.contract.ItemContract;
import id.ac.polinema.seameo.ecanteen.contract.TransactionContract;
import id.ac.polinema.seameo.ecanteen.model.ItemModel;
import id.ac.polinema.seameo.ecanteen.model.TransactionModel;
import id.ac.polinema.seameo.ecanteen.presenter.Presenter;
import id.ac.polinema.seameo.ecanteen.view.activity.ScanActivity;

public class TransactionPresenter extends Presenter implements TransactionContract.Presenter {
    private final String TAG = "TRANSACTION_PRESENTER";

    public TransactionPresenter() {
        super();
    }

    @Override
    public void all(TransactionContract.Callback callback) {
        super.getFirestore(App.TRANSACTION_COLLECTION, transactionCallback(callback));
    }

    @Override
    public void get(ItemContract.ScannerResult.Callback callback) {
        super.getRealtimeDB(getItem(callback));
    }

    @Override
    public void save(TransactionModel ob) {
        HashMap<String, Object> params = new HashMap<>();

        params.put(TransactionModel.NAME, ob.getName());
        params.put(TransactionModel.DATE_TIME, new Timestamp(new Date()));
        params.put(TransactionModel.ITEMS, ob.getItems());
        params.put(TransactionModel.MONEY, ob.getMoney());
        params.put(TransactionModel.PAYMENT, ob.getPayment());
        params.put(TransactionModel.CASHBACK, ob.getCashback());

        super.storeFirestore(params);
    }

    @Override
    public void onAttach(FragmentTransaction ft, Fragment fg) {
        ft = ft.replace(ScanActivity.container, fg);

        super.onAttach(ft, fg);
    }

    private OnCompleteListener transactionCallback(final TransactionContract.Callback callback) {
        return new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    callback.run(task.getResult());
                }
            }
        };
    }

    private ValueEventListener getItem(final ItemContract.ScannerResult.Callback callback) {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<ItemModel> list = new ArrayList<>();
                String key = "";

                for (DataSnapshot it : dataSnapshot.getChildren()) {
                    ItemModel item = it.getValue(ItemModel.class);
                    item.setKey(it.getKey());
                    list.add(item);
                    key = it.getKey();
                }

                callback.setView(list, key);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        };
    }

}
