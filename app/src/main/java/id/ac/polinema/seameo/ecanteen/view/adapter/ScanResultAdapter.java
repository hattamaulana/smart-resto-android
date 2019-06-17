package id.ac.polinema.seameo.ecanteen.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.ac.polinema.seameo.ecanteen.R;
import id.ac.polinema.seameo.ecanteen.contract.ListResultContract;
import id.ac.polinema.seameo.ecanteen.model.ItemModel;
import id.ac.polinema.seameo.ecanteen.presenter.scan.ListResultPresenter;

public class ScanResultAdapter extends RecyclerView.Adapter<ScanResultAdapter.Holder> implements ListResultContract.View {
    private final String TAG = "SCAN_RESULT_ADAPTER";

    private ListResultPresenter mPresenter;
    private ArrayList<ItemModel> mListItem;
    private Context mContext;

    public ScanResultAdapter(ArrayList<ItemModel> list, Context context){
        this.mListItem = list;
        this.mContext = context;
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
        holder.txtCount.setText(item.getCount());
        holder.btnIncrement.setOnClickListener(btnIncrementClicked(item.getId(), holder.txtCount));
        holder.btnDecrement.setOnClickListener(btnDecrementClicked(item.getId(), holder.txtCount));
    }

    @Override
    public int getItemCount() {
        if (mListItem == null && mListItem.size() == 0)
            return 0;
        else
            return mListItem.size();
    }

    private View.OnClickListener btnIncrementClicked(final String id, final TextView textView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.valueOf(textView.getText().toString());
                    x++;
                textView.setText(String.valueOf(x));
                mPresenter.update(id, x);
            }
        };
    }

    private View.OnClickListener btnDecrementClicked(final String id, final TextView textView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.valueOf(textView.getText().toString());
                if (x > 0) {
                    x--;
                    textView.setText(String.valueOf(x));
                    mPresenter.update(id, x);
                }
            }
        };
    }

    @Override
    public void initPresenter() {
        mPresenter = new ListResultPresenter();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtCount;
        private ImageView btnIncrement;
        private ImageView btnDecrement;

        public Holder(@NonNull View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_item_name);
            txtCount = (TextView) itemView.findViewById(R.id.txt_item_count);
            btnIncrement = (ImageView) itemView.findViewById(R.id.btn_increment);
            btnDecrement = (ImageView) itemView.findViewById(R.id.btn_decrement);
        }
    }
}
