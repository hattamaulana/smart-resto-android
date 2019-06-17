package id.ac.polinema.seameo.ecanteen.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import id.ac.polinema.seameo.ecanteen.R;
import id.ac.polinema.seameo.ecanteen.contract.ItemContract;
import id.ac.polinema.seameo.ecanteen.contract.TransactionContract;
import id.ac.polinema.seameo.ecanteen.model.ItemModel;
import id.ac.polinema.seameo.ecanteen.model.TransactionModel;
import id.ac.polinema.seameo.ecanteen.presenter.transaction.TransactionPresenter;

public class TransactionFragment extends Fragment implements TransactionContract.View {
    private static final String TAG = "TRANSACTION_FRAGMENT";
    private TransactionPresenter mPresenter;
    private String[] mItems;
    private EditText mName;
    private EditText mMoney;
    private int mCashback;
    private int mPayment;

    @Override
    public void initPresenter() {
        mPresenter = new TransactionPresenter();
    }

    @Override
    public void onStart() {
        super.onStart();

        mPayment = 0;
        mCashback = 0;
//        mPresenter.get(itemCallback);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transaction, container, false);

        initPresenter();
        initView(v);

        return v;
    }

//    private ItemContract.ScannerResult.Callback itemCallback = new ItemContract.ScannerResult.Callback() {
//        @Override
//        public void setView(ArrayList<ItemModel> list) {
//            Log.i(TAG, "setView: "+ list.size());
//
//            mItems = new String[list.size()];
//            for (int i = 0; i < list.size(); i++) {
//                mItems[i] = list.get(i).getId();
//                mPayment = list.get(i).getPrice() * list.get(i).getCount();
//            }
//        }
//    };

    private View.OnClickListener storeTransaction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TransactionModel transaction = new TransactionModel();
            int money = Integer.valueOf(mMoney.getText().toString());
            mCashback = money - mPayment;

            transaction.setName(mName.getText().toString());
            transaction.setMoney(money);
            transaction.setItems(mItems);
            transaction.setPayment(mPayment);
            transaction.setCashback(mCashback);

            mPresenter.save(transaction);
        }
    };

    public void initView(View v) {
        mName = (EditText) v.findViewById(R.id.edtxt_name);
        mMoney = (EditText) v.findViewById(R.id.edtxt_money);

        Button btnStoreTransaction = (Button) v.findViewById(R.id.btn_pay);
               btnStoreTransaction.setOnClickListener(storeTransaction);
    }
}
