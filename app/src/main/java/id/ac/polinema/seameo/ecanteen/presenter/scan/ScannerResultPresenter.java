package id.ac.polinema.seameo.ecanteen.presenter.scan;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import id.ac.polinema.seameo.ecanteen.contract.ItemContract;
import id.ac.polinema.seameo.ecanteen.model.ItemModel;
import id.ac.polinema.seameo.ecanteen.presenter.Presenter;

public class ScannerResultPresenter extends Presenter implements ItemContract.ScannerResult.Presenter {

    public ScannerResultPresenter() {
        super();
    }

    @Override
    public void show(final ItemContract.ScannerResult.Callback callback) {
        super.getRealtimeDB(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean saveNext = true;
                ArrayList<ItemModel> list = new ArrayList<>();

                for (DataSnapshot it: dataSnapshot.getChildren()) {
                    ItemModel item = it.getValue(ItemModel.class);
                              item.setCount(0);

                    for (DataSnapshot i: dataSnapshot.getChildren()) {
                        if (i.getKey().equals(item.getId())) {
                            item.setCount(item.getCount() + 1);
                            saveNext = false;
                        }
                    }

                    if (saveNext)
                        list.add(item);
                }

                callback.setView(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
