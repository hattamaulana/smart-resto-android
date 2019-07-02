/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 10:45 AM
 */

package id.ac.polinema.seameo.ecanteen.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public interface BasePresenter {
    void getFirestore(String s, OnCompleteListener listener);
    void storeFirestore(HashMap<String, Object> args);

    void removeRealtimeDB(String s);
    void getRealtimeDB(ValueEventListener callback);
    void storeRealtimeDB(Object ob);

    void onAttach(FragmentTransaction ft, Fragment fg);
}
