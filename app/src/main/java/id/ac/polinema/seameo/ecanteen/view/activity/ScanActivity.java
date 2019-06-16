package id.ac.polinema.seameo.ecanteen.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import id.ac.polinema.seameo.ecanteen.R;
import id.ac.polinema.seameo.ecanteen.contract.ItemContract;
import id.ac.polinema.seameo.ecanteen.presenter.scan.ScannerPresenter;
import id.ac.polinema.seameo.ecanteen.view.fragment.ScannerFragment;

public class ScanActivity extends AppCompatActivity implements ItemContract.View {
    public static final int container = R.id.frame_scan_activity;

    private ScannerPresenter mPresenter;

    @Override
    public void initPresenter() {
        mPresenter = new ScannerPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        initPresenter();
        initView();
    }

    @Override
    protected void onStop() {
        mPresenter.remove();
        super.onStop();
    }

    private void initView() {
        FragmentTransaction fragment = getSupportFragmentManager().beginTransaction();

        // Set Default View as Scanner
        mPresenter.onAttach(fragment, new ScannerFragment());
    }
}
