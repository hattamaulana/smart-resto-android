package id.ac.polinema.seameo.ecanteen.presenter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import id.ac.polinema.seameo.ecanteen.App;

import static android.support.constraint.Constraints.TAG;

public class Presenter implements BasePresenter {
    protected FirebaseFirestore mFirestore;
    protected DatabaseReference mReatimeDb;
    private Object result;

    public Presenter() {
        // Instance Firebase Firestore
        mFirestore = FirebaseFirestore.getInstance();

        // Instance Firebase Realtime Database
        mReatimeDb = FirebaseDatabase.getInstance().getReference(App.MAIN_REFERENCE);
    }

    @Override
    public void getFirestore(OnCompleteListener listener) {
        mFirestore.collection(App.ITEM_COLLECTION).get().addOnCompleteListener(listener);
    }

    @Override
    public void getRealtimeDB(ValueEventListener callback) {
        final ArrayList<Object> list = new ArrayList<>();

        mReatimeDb.child(App.ORDER_REFERENCE).addValueEventListener(callback);
    }

    @Override
    public void storeFirestore(Object ob) {

    }

    @Override
    public void storeRealtimeDB(Object ob) {
        mReatimeDb.child(App.ORDER_REFERENCE).push().setValue(ob);
    }

    @Override
    public void removeFirestore(String s) {

    }

    @Override
    public void removeRealtimeDB(String s) {
        mReatimeDb.child(s).setValue("");
    }

    @Override
    public void onAttach(FragmentTransaction ft, Fragment fg) {
        ft.commit();
    }
}
