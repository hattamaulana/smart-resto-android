/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 7/2/19 4:31 PM
 */

package id.ac.polinema.seameo.ecanteen.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import id.ac.polinema.seameo.ecanteen.App;
import id.ac.polinema.seameo.ecanteen.R;
import id.ac.polinema.seameo.ecanteen.contract.ListResultContract;
import id.ac.polinema.seameo.ecanteen.model.ItemModel;
import id.ac.polinema.seameo.ecanteen.presenter.scan.ListResultPresenter;

public class ScanResultAdapter extends RecyclerView.Adapter<ScanResultAdapter.Holder> implements ListResultContract.View {
    private ListResultPresenter mPresenter;
    private ArrayList<ItemModel> mListItem;
    private Context mContext;
    private String mKey;

    public ScanResultAdapter(ArrayList<ItemModel> list, Context context, String id){
        this.mListItem = list;
        this.mContext = context;
        this.mKey = id;
    }

    @Override
    public void initPresenter() {
        mPresenter = new ListResultPresenter();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                 .inflate(R.layout.adapter_scan_result, viewGroup, false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        ItemModel item = mListItem.get(i);

        holder.txtName.setText(item.getName());
        holder.txtCount.setText(String.valueOf(item.getCount()));
        holder.btnIncrement.setOnClickListener(btnIncrementClicked(item, holder));
        holder.btnDecrement.setOnClickListener(btnDecrementClicked(item, holder));
        holder.btnDelete.setOnClickListener(btnDeleteClicked(item));
    }

    @Override
    public int getItemCount() {
        if (mListItem == null && mListItem.size() == 0)
            return 0;
        else
            return mListItem.size();
    }

    private View.OnClickListener btnIncrementClicked(final ItemModel item, final Holder holder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.valueOf(holder.txtCount.getText().toString()) + 1;
                item.setCount(x);

                FirebaseDatabase
                    .getInstance()
                    .getReference(App.MAIN_REFERENCE)
                    .child(App.ORDER_REFERENCE).child(item.getKey())
                    .updateChildren(item.toMap(item));
            }
        };
    }

    private View.OnClickListener btnDecrementClicked(final ItemModel item, final Holder holder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.valueOf(holder.txtCount.getText().toString());
                if (x > 1) {
                    item.setCount((x - 1));

                    FirebaseDatabase
                        .getInstance()
                        .getReference(App.MAIN_REFERENCE)
                        .child(App.ORDER_REFERENCE).child(item.getKey())
                        .updateChildren(item.toMap(item));
                }
            }
        };
    }

    private View.OnClickListener btnDeleteClicked(final ItemModel item){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase
                        .getInstance()
                        .getReference(App.MAIN_REFERENCE)
                        .child(App.ORDER_REFERENCE).child(item.getKey())
                        .removeValue();
            }
        };
    }

    class Holder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtCount;
        private ImageView btnIncrement;
        private ImageView btnDecrement;
        private ImageView btnDelete;

        public Holder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_item_name);
            txtCount = itemView.findViewById(R.id.txt_item_count);
            btnIncrement = itemView.findViewById(R.id.btn_increment);
            btnDecrement = itemView.findViewById(R.id.btn_decrement);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
