/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 10:45 AM
 */

package id.ac.polinema.seameo.ecanteen.contract;

import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

import id.ac.polinema.seameo.ecanteen.model.ItemModel;
import id.ac.polinema.seameo.ecanteen.presenter.BasePresenter;
import id.ac.polinema.seameo.ecanteen.view.BaseView;

public class ItemContract {
    public static class Scanner {
        public interface Presenter extends BasePresenter {
            void find(String s, Callback callback);
            void save(ItemModel it);
            void remove();
        }

        public interface Callback {
            void onSuccess(QueryDocumentSnapshot q);
            void onFailed();
        }
    }

    public static class ScannerResult {
        public interface Presenter extends BasePresenter {
            void show(Callback callback);
        }

        public interface Callback {
            void setView(ArrayList<ItemModel> list, String id);
        }
    }

    public interface View extends BaseView {  }
}
