package id.ac.polinema.seameo.ecanteen.presenter.scan;

import id.ac.polinema.seameo.ecanteen.App;
import id.ac.polinema.seameo.ecanteen.presenter.Presenter;
import id.ac.polinema.seameo.ecanteen.contract.ListResultContract;

public class ListResultPresenter extends Presenter implements ListResultContract.Presenter {

    public ListResultPresenter() {
        super();
    }

    @Override
    public void update(String child, int i) {
        mReatimeDb.child(App.ORDER_REFERENCE).child(child)
                .setValue(i);
    }
}
