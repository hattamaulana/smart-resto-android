/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/24/19 12:14 PM
 */

package id.ac.polinema.seameo.ecanteen.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import id.ac.polinema.seameo.ecanteen.R;
import id.ac.polinema.seameo.ecanteen.contract.ItemContract;
import id.ac.polinema.seameo.ecanteen.presenter.scan.ScannerPresenter;
import id.ac.polinema.seameo.ecanteen.view.fragment.ScannerFragment;

public class ScanActivity extends AppCompatActivity implements ItemContract.View {
    public static final int container = R.id.frame_scan_activity;
    public static boolean deleteData = true;

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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Peringatan")
                .setMessage("Apakah Anda Ingin Keluar Dari Aplikasi ini ? ")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    /**
     * @method initView, used to initiation all component view
     */
    private void initView() {
        // Set Default View as Scanner
        mPresenter.onAttach(getSupportFragmentManager().beginTransaction(), new ScannerFragment());
    }
}
