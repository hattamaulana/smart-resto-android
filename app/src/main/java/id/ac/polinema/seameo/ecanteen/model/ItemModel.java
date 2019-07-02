/*
 * Copyright (c) 2019.
 *
 * Author: Mahatta Maulana
 * Github: https://github.com/hattamaulana
 *
 * Last Modified at 6/21/19 12:14 PM
 */

package id.ac.polinema.seameo.ecanteen.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.meta.Exclusive;

public class ItemModel {
    public static String KEY = "key";
    public static String ID = "id";
    public static String NAME = "name";
    public static String IMAGE_URI = "imgUri";
    public static String PRICE = "price";
    public static String COUNT = "count";

    private String key;
    private String id;
    private String name;
    private String imgUri;
    private int price;
    private int count;

    @Exclusive
    public Map<String, Object> toMap(ItemModel item) {
        HashMap<String, Object> data = new HashMap<>();

        data.put(ID, item.getId());
        data.put(NAME, item.getName());
        data.put(IMAGE_URI, item.getImgUri());
        data.put(PRICE, item.getPrice());
        data.put(COUNT, item.getCount());

        return data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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
