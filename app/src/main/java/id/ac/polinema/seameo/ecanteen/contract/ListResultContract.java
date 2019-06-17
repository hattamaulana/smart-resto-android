package id.ac.polinema.seameo.ecanteen.contract;

import id.ac.polinema.seameo.ecanteen.presenter.BasePresenter;
import id.ac.polinema.seameo.ecanteen.view.BaseView;

public class ListResultContract {
    public interface Presenter extends BasePresenter {
        void update(String child, int i);
    }

    public interface View extends BaseView { }
}
