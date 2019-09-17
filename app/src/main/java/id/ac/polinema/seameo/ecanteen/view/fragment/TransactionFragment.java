/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 7/2/19 4:31 PM
 */

package id.ac.polinema.seameo.ecanteen.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import id.ac.polinema.seameo.ecanteen.App;
import id.ac.polinema.seameo.ecanteen.R;
import id.ac.polinema.seameo.ecanteen.contract.ItemContract;
import id.ac.polinema.seameo.ecanteen.contract.TransactionContract;
import id.ac.polinema.seameo.ecanteen.model.ItemModel;
import id.ac.polinema.seameo.ecanteen.model.KitchenQueueModel;
import id.ac.polinema.seameo.ecanteen.model.TransactionModel;
import id.ac.polinema.seameo.ecanteen.presenter.transaction.TransactionPresenter;
import id.ac.polinema.seameo.ecanteen.view.activity.ScanActivity;
import id.ac.polinema.seameo.ecanteen.view.adapter.TransactionAdapter;

public class TransactionFragment extends Fragment implements TransactionContract.View {
    private static final String TAG = "TRANSACTION_FRAGMENT";

    private TransactionPresenter mPresenter;
    private ArrayList<String> mItems;
    private ArrayList<ItemModel> mListResult;
    private EditText mName;
    private EditText mMoney;
    private TextView mCountPay;
    private int mCashback;
    private int mPayment;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private TransactionAdapter mAdapter;

    @Override
    public void initPresenter() {
        mPresenter = new TransactionPresenter();
    }

    @Override
    public void onStart() {
        super.onStart();

        mItems = new ArrayList<>();
        mPayment = 0;
        mCashback = 0;

        mPresenter.get(itemCallback);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transaction, container, false);

        initPresenter();
        initView(v);

        return v;
    }

    private ItemContract.ScannerResult.Callback itemCallback = new ItemContract.ScannerResult.Callback() {
        @Override
        public void setView(ArrayList<ItemModel> list, String id) {
            if (list.size() > 0) {
                mListResult = list;
                mAdapter = new TransactionAdapter(list, getContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

                for (ItemModel item : list) {
                    mPayment += item.getPrice() * item.getCount();

                    for (int i = 1; i <= item.getCount(); i++)
                        mItems.add(item.getId());
                }

                mCountPay.setText("Rp "+ mPayment);
            }
        }
    };

    private View.OnClickListener storeTransaction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String errorMessage = "";
            boolean isEmptyForName = mName.getText().toString().equals("");
            boolean isEmptyForMoney = mMoney.getText().toString().equals("");

            if (! (isEmptyForName && isEmptyForMoney) ) {
                TransactionModel transaction = new TransactionModel();
                int money = Integer.valueOf(mMoney.getText().toString());
                mCashback = money - mPayment;

                transaction.setName(mName.getText().toString());
                transaction.setMoney(money);
                transaction.setItems(mItems);
                transaction.setPayment(mPayment);
                transaction.setCashback(mCashback);
                mPresenter.save(transaction);

                ArrayList<KitchenQueueModel.Menu> menus = new ArrayList<>();

                for (ItemModel it: mListResult)
                    menus.add(new KitchenQueueModel.Menu(it.getName(), it.getCount()));

                KitchenQueueModel queue = new KitchenQueueModel(
                        mName.getText().toString(),
                        menus
                );

                FirebaseDatabase.getInstance()
                        .getReference(App.MAIN_REFERENCE).child(App.KITCHEN_QUEUE)
                        .push().setValue(queue);

                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext())
                        .setPositiveButton("Ok", positiveButtonCallback);

                if (mCashback > 0)
                    dialog.setMessage("Kembalian "+ mCashback).create().show();
                else
                    dialog.setMessage("Terimakasih Telah Membayar dengan Uang Pas").create().show();
            } else {
                if (isEmptyForName)
                    errorMessage = "Nama";

                if (isEmptyForMoney)
                errorMessage += (errorMessage.equals("")) ? "Nominal Uang" : " & Nominal Uang";
            }

            if (! errorMessage.equals("")) {
                errorMessage = "Text Field "+ errorMessage + " Tidak Boleh Kosong.";
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void initView(View v) {
        mName = v.findViewById(R.id.edtxt_name);
        mMoney = v.findViewById(R.id.edtxt_money);
        mCountPay = v.findViewById(R.id.txt_payment);
        mRecyclerView = v.findViewById(R.id.list_transaction);

        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayout.VERTICAL);

        Button btnStoreTransaction = v.findViewById(R.id.btn_pay);
        btnStoreTransaction.setOnClickListener(storeTransaction);
    }

    private DialogInterface.OnClickListener positiveButtonCallback = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();

            ScanActivity.deleteData = true;
            getFragmentManager().beginTransaction()
            .replace(ScanActivity.container, new ScannerFragment())
            .commit();
        }
    };
}
