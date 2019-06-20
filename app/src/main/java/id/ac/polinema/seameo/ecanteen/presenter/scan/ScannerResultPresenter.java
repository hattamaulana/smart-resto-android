package id.ac.polinema.seameo.ecanteen.presenter.scan;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import id.ac.polinema.seameo.ecanteen.contract.ItemContract;
import id.ac.polinema.seameo.ecanteen.model.ItemModel;
import id.ac.polinema.seameo.ecanteen.presenter.Presenter;

public class ScannerResultPresenter extends Presenter implements ItemContract.ScannerResult.Presenter {
    private final String TAG = "SCANNER_RESULT_PRESENTER";

    public ScannerResultPresenter() {
        super();
    }

    @Override
    public void show(final ItemContract.ScannerResult.Callback callback) {
        super.getRealtimeDB(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<ItemModel> list = new ArrayList<>();
                String key = "";

                for (DataSnapshot it : dataSnapshot.getChildren()) {
                    ItemModel item = it.getValue(ItemModel.class);
                    item.setKey(it.getKey());
                    list.add(item);
                    key = it.getKey();
                }

                callback.setView(list, key);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
}
