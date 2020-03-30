package com.rokad.mobile_recharge;

import com.rokad.R;

public class SubscriberModule {
    private int id = 0;
    private int image = R.drawable.airtel;
    private String name = "Airtel";
    private String key = "A";
    private boolean isSelected =false;
    SubscriberModule(int _id, int _image, String _name, String _key) {
        id = _id;
        image = _image;
        name = _name;
        key = _key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
