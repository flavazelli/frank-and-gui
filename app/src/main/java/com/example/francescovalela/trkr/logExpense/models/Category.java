package com.example.francescovalela.trkr.logExpense.models;

import java.util.UUID;

/**
 * Created by francescovalela on 2017-02-12.
 *
 * Allows for
 */

public class Category {

    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }






}
