/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 10:45 AM
 */

package id.ac.polinema.seameo.ecanteen.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.ac.polinema.seameo.ecanteen.R;
import id.ac.polinema.seameo.ecanteen.model.HistoryModel;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Holder> {
    private Context context;
    private List<HistoryModel> histories;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    public void setHistories(List<HistoryModel> histories) {
        this.histories = histories;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(
                LayoutInflater.from(context)
                        .inflate(R.layout.adapter_history, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        private TextView _orderName;
        private TextView _waktuOrder;
        private RecyclerView _items;
//        private TextView _orderItemName;
//        private TextView _orderItemPrice;
//        private TextView _orderCount;
//        private TextView _total;

        public Holder(@NonNull View itemView) {
            super(itemView);

            _orderName = itemView.findViewById(R.id.order_name);
            _waktuOrder = itemView.findViewById(R.id.waktu_order);
            _items = itemView.findViewById(R.id.recycler);
//            _orderItemName = itemView.findViewById(R.id.order_item_name);
//            _orderItemPrice = itemView.findViewById(R.id.order_item_price);
//            _orderCount = itemView.findViewById(R.id.order_count);
//            _total = itemView.findViewById(R.id.order_count_price);
        }
    }
}
