package id.ac.polinema.seameo.ecanteen.presenter.transaction;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.HashMap;

import id.ac.polinema.seameo.ecanteen.App;
import id.ac.polinema.seameo.ecanteen.contract.ItemContract;
import id.ac.polinema.seameo.ecanteen.contract.TransactionContract;
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

    private ValueEventListener getItem(ItemContract.ScannerResult.Callback callback) {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
    }

}