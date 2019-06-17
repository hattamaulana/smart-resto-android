package id.ac.polinema.seameo.ecanteen.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public interface BasePresenter {
    void getFirestore(String s, OnCompleteListener listener);
    void getRealtimeDB(ValueEventListener callback);

    void storeFirestore(HashMap<String, Object> args);
    void storeRealtimeDB(Object ob);

    void removeFirestore(String s);
    void removeRealtimeDB(String s);

    void onAttach(FragmentTransaction ft, Fragment fg);
}
