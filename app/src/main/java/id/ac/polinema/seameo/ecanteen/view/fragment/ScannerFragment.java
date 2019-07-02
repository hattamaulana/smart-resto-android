/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/25/19 9:51 AM
 */

package id.ac.polinema.seameo.ecanteen.view.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import id.ac.polinema.seameo.ecanteen.contract.ItemContract;
import id.ac.polinema.seameo.ecanteen.model.ItemModel;
import id.ac.polinema.seameo.ecanteen.presenter.scan.ScannerPresenter;
import id.ac.polinema.seameo.ecanteen.view.activity.ScanActivity;

public class ScannerFragment extends Fragment implements ItemContract.View {
    private String TAG = "SCANNER_FRAGMENT";
    private ScannerPresenter mPresenter;
    private ProgressDialog mProggressDialog;
    private AlertDialog mAlertDialog;
    private int mBack = 0;

    @Override
    public void initPresenter() {
        mPresenter = new ScannerPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initPresenter();
        initView();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        switch (mBack) {
            case 0 :
                IntentIntegrator.forSupportFragment(this)
                        .setOrientationLocked(true)
                        .setPrompt("Scann Barcode Sekarang")
                        .setOrientationLocked(false)
                        .setCameraId(0)
                        .setBarcodeImageEnabled(false)
                        .initiateScan();
                break;
            case 1 :
                mAlertDialog.show();
                break;
            case 2 :
                mProggressDialog.show();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult res = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        Log.i(TAG, "onActivityResult: "+ res.getContents());

        if (res != null) {
            if (res.getContents() == null)
                mBack = 1;
            else {
                mBack = 2;
                processResult(res.getContents());
            }
        } else
            super.onActivityResult(requestCode, resultCode, data);
    }

    private void processResult(String res) {
        mPresenter.find(res, callbackSave);
    }

    private void initView() {
        mAlertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Peringatan")
                .setMessage("Jika Anda Kembali Data yang Sudah di Scan Akan Hilang.")
                .setPositiveButton("Biarkan", positiveButton())
                .setNegativeButton("Batalkan", negativeButton())
                .create();

        mProggressDialog = new ProgressDialog(getContext());
        mProggressDialog.setMessage("Loading");
    }

    private ItemContract.Scanner.Callback callbackSave = new ItemContract.Scanner.Callback() {
        @Override
        public void onSuccess(QueryDocumentSnapshot q) {
            mProggressDialog.dismiss();
            ItemModel item = q.toObject(ItemModel.class);
                      item.setId(q.getId());
                      item.setCount(1);

            mPresenter.save(item);

            getFragmentManager().beginTransaction()
                .replace(ScanActivity.container, new ScanResultFragment())
                .commitAllowingStateLoss();
        }

        @Override
        public void onFailed() {
            mBack = 0;
            Toast.makeText(getContext(), "QR-Code Tidak dikenali", Toast.LENGTH_SHORT).show();
            onStart();
        }
    };

    private DialogInterface.OnClickListener positiveButton() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        };
    }

    private DialogInterface.OnClickListener negativeButton() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mAlertDialog.dismiss();
                mBack = 0;
                onStart();
            }
        };
    }
}