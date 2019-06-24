package id.ac.polinema.seameo.ecanteen;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.google.firebase.FirebaseApp;

public class App extends Application {
    private final String TAG = "APP";

    // Firestore
    public static final String ITEM_COLLECTION = "items";
    public static final String TRANSACTION_COLLECTION = "transactions";

    // Realtime Database
    public static final String MAIN_REFERENCE = "ecanteen";
    public static final String ORDER_REFERENCE = "order";
    public static final String KITCHEN_QUEUE = "queue";

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
