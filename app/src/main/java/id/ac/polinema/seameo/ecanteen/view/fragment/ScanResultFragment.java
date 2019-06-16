package id.ac.polinema.seameo.ecanteen.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.ac.polinema.seameo.ecanteen.R;
import id.ac.polinema.seameo.ecanteen.contract.ItemContract;
import id.ac.polinema.seameo.ecanteen.model.ItemModel;
import id.ac.polinema.seameo.ecanteen.presenter.scan.ScannerResultPresenter;

import static android.support.constraint.Constraints.TAG;

public class ScanResultFragment extends Fragment implements ItemContract.View {
    private ScannerResultPresenter mPresenter;

    @Override
    public void initPresenter() {
        mPresenter = new ScannerResultPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initPresenter();

        View v = inflater.inflate(R.layout.fragment_scan_result, container, false);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        mPresenter.show(new ItemContract.ScannerResult.Callback() {
            @Override
            public void setView(ArrayList<ItemModel> list) {
                // Set List Adapter
                Log.i(TAG, "setView: "+ list.size());
            }
        });
    }
}
