package id.ac.polinema.seameo.ecanteen.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemModel {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String IMAGE_URI = "imgUri";
    public static final String PRICE = "price";
    public static final String COUNT = "count";

    private String id;
    private String name;
    private String imgUri;
    private int price;
    private int count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
