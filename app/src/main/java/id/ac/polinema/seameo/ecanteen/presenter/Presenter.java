package id.ac.polinema.seameo.ecanteen.presenter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

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

public class Presenter implements BasePresenter {
    protected FirebaseFirestore mFirestore;
    protected DatabaseReference mReatimeDb;

    public Presenter() {
        // Instance Firebase Firestore
        mFirestore = FirebaseFirestore.getInstance();

        // Instance Firebase Realtime Database
        mReatimeDb = FirebaseDatabase.getInstance().getReference(App.MAIN_REFERENCE);
    }

    @Override
    public Object findFirestore(final String id, final Class ob) {
        final Object[] temp = new Object[1];

        mFirestore.collection(App.ITEM_COLLECTION).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot q : task.getResult()) {
                                if (q.getId().equals(id)) {
                                    temp[0] = q.toObject(ob);
                                }
                            }
                        }
                    }
                });

        return temp[0];
    }

    @Override
    public Object findRealtimeDB(String id) {
        return null;
    }

    @Override
    public ArrayList<Object> allFirestore() {
        return null;
    }

    @Override
    public ArrayList<Object> allRealtimeDB(final Class c) {
        final ArrayList<Object> list = new ArrayList<>();

        mReatimeDb.child(App.ORDER_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot it: dataSnapshot.getChildren()) {
                    list.add(it.getValue(c));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return list;
    }

    @Override
    public void storeFirestore(Object ob) {

    }

    @Override
    public void storeRealtimeDB(Object ob) {

    }

    @Override
    public void removeFirestore(String s) {

    }

    @Override
    public void removeRealtimeDB(String s) {
        mReatimeDb.child(s).setValue("");
    }

    @Override
    public void onAttach(FragmentManager fM, Fragment fg) {
        fM.beginTransaction().attach(fg).commit();
    }
}
