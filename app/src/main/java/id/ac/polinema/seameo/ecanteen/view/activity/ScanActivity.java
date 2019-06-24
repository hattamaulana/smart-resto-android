package id.ac.polinema.seameo.ecanteen.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import id.ac.polinema.seameo.ecanteen.R;
import id.ac.polinema.seameo.ecanteen.contract.ItemContract;
import id.ac.polinema.seameo.ecanteen.presenter.scan.ScannerPresenter;
import id.ac.polinema.seameo.ecanteen.view.fragment.ScannerFragment;

public class ScanActivity extends AppCompatActivity implements ItemContract.View {
    public static final int container = R.id.frame_scan_activity;
    public static boolean deleteData = true;

    private final String TAG = "SCAN_ACTIVITY";

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
        if (deleteData) {
            mPresenter.remove();
        } else {
            deleteData = true;
        }

        super.onStop();
    }

    private void initView() {
        FragmentTransaction fragment = getSupportFragmentManager().beginTransaction();

        // Set Default View as Scanner
        mPresenter.onAttach(fragment, new ScannerFragment());
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Peringatan");
        alert.setMessage("Apakah Anda Ingin Keluar Dari Aplikasi ini ? ");
        alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.create();
        alert.show();
    }
}
