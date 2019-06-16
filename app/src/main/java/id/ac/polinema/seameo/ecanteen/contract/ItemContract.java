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
            void setView(ArrayList<ItemModel> list);
        }
    }

    public interface View extends BaseView {  }
}
