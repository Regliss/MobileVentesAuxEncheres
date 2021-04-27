package com.example.mobileventesauxencheres.models;

import java.io.Serializable;

public class ApiRecords implements Serializable {
    private ApiFields fields;

    public ApiFields getFields() {
        return fields;
    }

    public void setFields(ApiFields fields) {
        this.fields = fields;
    }
}
