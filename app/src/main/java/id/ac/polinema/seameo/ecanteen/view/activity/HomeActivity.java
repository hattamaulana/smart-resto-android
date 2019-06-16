package id.ac.polinema.seameo.ecanteen.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import id.ac.polinema.seameo.ecanteen.R;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
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
}
