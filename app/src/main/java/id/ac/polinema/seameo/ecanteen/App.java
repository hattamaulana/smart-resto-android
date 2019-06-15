package id.ac.polinema.seameo.ecanteen;

import android.app.Application;

public class App extends Application {
    // Firestore
    public static final String ITEM_COLLECTION = "items";
    public static final String TRANSACTION_COLLECTION = "transactions";

    // Realtime Database
    public static final String MAIN_REFERENCE = "ecanteen";
    public static final String ORDER_REFERENCE = "order";
}
