package id.ac.polinema.seameo.ecanteen.contract;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

import id.ac.polinema.seameo.ecanteen.presenter.BasePresenter;
import id.ac.polinema.seameo.ecanteen.view.BaseView;

public class ListResultContract {

    public interface Presenter extends BasePresenter {
        void update(String id, String data);
    }

    public interface View extends BaseView { }
}
