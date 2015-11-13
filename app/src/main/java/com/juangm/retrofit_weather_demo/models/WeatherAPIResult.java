package com.juangm.retrofit_weather_demo.models;

import java.util.ArrayList;
import java.util.List;

//Main result for API call
public class WeatherAPIResult {

    private City city;
    private int cod;
    private double message;
    private int cnt;
    private List<Day> list = new ArrayList<Day>();

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<Day> getList() {
        return list;
    }

    public void setList(List<Day> list) {
        this.list = list;
    }
}
