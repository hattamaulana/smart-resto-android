package id.ac.polinema.seameo.ecanteen.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import id.ac.polinema.seameo.ecanteen.R;
import id.ac.polinema.seameo.ecanteen.contract.TransactionContract;
import id.ac.polinema.seameo.ecanteen.model.TransactionModel;
import id.ac.polinema.seameo.ecanteen.presenter.transaction.TransactionPresenter;

public class HomeActivity extends AppCompatActivity implements TransactionContract.View {
    private final String TAG = "HOME_ACTIVITY";

    private TransactionPresenter mPresenter;
    private ArrayList<TransactionModel> mListHistory;

    @Override
    public void initPresenter() {
        mPresenter = new TransactionPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initPresenter();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mPresenter.all(getHistories);
    }

    private void initView() {
        FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.scann_barcode);
        btn.setOnClickListener(onClickFAButton());
    }

    private View.OnClickListener onClickFAButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ScanActivity.class));
            }
        };
    }

    private TransactionContract.Callback getHistories = new TransactionContract.Callback() {
        @Override
        public void run(QuerySnapshot q) {
            for (QueryDocumentSnapshot doc : q) {
//                TransactionModel t = doc.toObject(TransactionModel.class);

//                Log.i(TAG, "run: "+ t.getName());
            }
        }
    };

}
