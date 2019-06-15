package id.ac.polinema.seameo.ecanteen.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

public interface BasePresenter {
    Object findFirestore(String id, Class ob);
    Object findRealtimeDB(String id);

    ArrayList<Object> allFirestore();
    ArrayList<Object> allRealtimeDB(Class c);

    void storeFirestore(Object ob);
    void storeRealtimeDB(Object ob);

    void removeFirestore(String s);
    void removeRealtimeDB(String s);

    void onAttach(FragmentManager fM, Fragment fg);
}
