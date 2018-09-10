package com.example.a59070090.healthy.weight;

/**
 * Created by LAB203_04 on 27/8/2561.
 */

public class Weight {
    private String date;
    private int weight;
    private String status;

    public Weight(String date, int weight, String status) {
        this.status = status;
        this.date = date;
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setMeasure(String status) {
        this.status = status;
    }
}
