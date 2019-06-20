package id.ac.polinema.seameo.ecanteen.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import id.ac.polinema.seameo.ecanteen.App;

public class Presenter implements BasePresenter {
    protected FirebaseFirestore mFirestore;
    protected DatabaseReference mRealtimeDb;

    private final String TAG = "PRESENTER";
    private Object result;

    public Presenter() {
        // Instance Firebase Firestore
        mFirestore = FirebaseFirestore.getInstance();

        // Instance Firebase Realtime Database
        mRealtimeDb = FirebaseDatabase.getInstance().getReference(App.MAIN_REFERENCE);
    }

    @Override
    public void getFirestore(String s, OnCompleteListener listener) {
        mFirestore.collection(s).get().addOnCompleteListener(listener);
    }

    @Override
    public void getRealtimeDB(ValueEventListener callback) {
        mRealtimeDb.child(App.ORDER_REFERENCE).addValueEventListener(callback);
    }

    @Override
    public void storeFirestore(HashMap<String, Object> args) {
        mFirestore.collection(App.TRANSACTION_COLLECTION)
                .add(args)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i(TAG, "onSuccess: Success Save Data");
                    }
                });
    }

    @Override
    public void storeRealtimeDB(Object ob) {
        mRealtimeDb.child(App.ORDER_REFERENCE).push().setValue(ob);
    }

    @Override
    public void removeFirestore(String s) {

    }

    @Override
    public void removeRealtimeDB(String s) {
        mRealtimeDb.child(s).setValue("");
    }

    @Override
    public void onAttach(FragmentTransaction ft, Fragment fg) {
        ft.commit();
    }
}
