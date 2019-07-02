/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 7/2/19 4:31 PM
 */

package id.ac.polinema.seameo.ecanteen.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import id.ac.polinema.seameo.ecanteen.R;
import id.ac.polinema.seameo.ecanteen.view.adapter.ScanResultAdapter;
import id.ac.polinema.seameo.ecanteen.contract.ItemContract;
import id.ac.polinema.seameo.ecanteen.model.ItemModel;
import id.ac.polinema.seameo.ecanteen.presenter.scan.ScannerResultPresenter;
import id.ac.polinema.seameo.ecanteen.view.activity.ScanActivity;

public class ScanResultFragment extends Fragment implements ItemContract.View {
    private final String TAG = "SCAN_RESULT_FRAGMENT";

    private ArrayList<ItemModel> mListItem;
    private ScannerResultPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ScanResultAdapter mAdapter;

    @Override
    public void initPresenter() {
        mPresenter = new ScannerResultPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_scan_result, container, false);
        initPresenter();
        initView(v);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        mPresenter.show(listItemCallback);
    }

    private void initView(View v) {
        mRecyclerView = v.findViewById(R.id.list_scan_result);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        Button scan = v.findViewById(R.id.btn_add_scan);
        scan.setOnClickListener(onButtonScanClicked);

        Button transaction = v.findViewById(R.id.btn_checkout);
        transaction.setOnClickListener(onButtonPayClicked);
    }

    private ItemContract.ScannerResult.Callback listItemCallback = new ItemContract.ScannerResult.Callback() {
        @Override
        public void setView(ArrayList<ItemModel> list, String id) {
            if (list.size() > 0) {
                mAdapter = new ScanResultAdapter(list, getContext(), id);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            } else {
                try {
                    Toast.makeText(getContext(), "Silahkan Lakukan Scanning Kembali", Toast.LENGTH_SHORT)
                         .show();

                    getFragmentManager()
                       .beginTransaction()
                       .replace(ScanActivity.container, new ScannerFragment())
                       .commit();

                } catch (NullPointerException e) { }
            }
        }
    };

    private View.OnClickListener onButtonScanClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ScanActivity.deleteData = false;
            getFragmentManager().beginTransaction()
                .replace(ScanActivity.container, new ScannerFragment())
                .commit();
        }
    };

    private View.OnClickListener onButtonPayClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ScanActivity.deleteData = false;
            getFragmentManager().beginTransaction()
                .replace(ScanActivity.container, new TransactionFragment())
                .commit();
        }
    };
}
