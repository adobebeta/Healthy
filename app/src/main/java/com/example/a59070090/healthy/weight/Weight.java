package com.example.a59070090.healthy.weight;

/**
 * Created by LAB203_04 on 27/8/2561.
 */

public class Weight {
    private String date;
    private String weight;


    public Weight(){

    }
    public Weight(String date, String weight) {
        this.date = date;
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

}
