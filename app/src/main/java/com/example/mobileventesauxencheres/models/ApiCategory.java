package com.example.mobileventesauxencheres.models;

import java.io.Serializable;

public class ApiCategory implements Serializable {
    private String title;
    private String _id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
