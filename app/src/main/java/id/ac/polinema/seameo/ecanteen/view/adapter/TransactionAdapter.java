package id.ac.polinema.seameo.ecanteen.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.ac.polinema.seameo.ecanteen.R;
import id.ac.polinema.seameo.ecanteen.model.ItemModel;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.Holder> {
    private final String TAG = "TRANSACTION_ADAPTER";

    private ArrayList<ItemModel> mListItem;
    private Context mContext;

    public TransactionAdapter(ArrayList<ItemModel> list, Context context) {
        mListItem = list;
        mContext = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_transaksi, viewGroup, false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        ItemModel item = mListItem.get(i);
        int payment = item.getPrice() * item.getCount();

        holder.mName.setText(item.getName());
        holder.mCount.setText("x " + item.getCount());
        holder.mPrice.setText("Rp " + item.getPrice());
        holder.mPayment.setText("Rp "+ payment);
    }

    @Override
    public int getItemCount() {
        if (mListItem != null && mListItem.size() > 0) {
            return mListItem.size();
        } else
            return 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mCount;
        private TextView mPrice;
        private TextView mPayment;

        public Holder(@NonNull View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.txt_name_trnsc);
            mCount = (TextView) itemView.findViewById(R.id.txt_count_trnsc);
            mPrice = (TextView) itemView.findViewById(R.id.txt_price_trnsc);
            mPayment = (TextView) itemView.findViewById(R.id.txt_payment_trnsc);
        }
    }
}
