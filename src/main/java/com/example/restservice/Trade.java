package com.example.restservice;

public class Trade {
    private int id;
    private String name;
    private double value;

    // Parametresiz yapıcı (no-args constructor)
    public Trade() {
    }

    // Parametreli yapıcı (opsiyonel)
    public Trade(int id, String name, double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    // Getter ve Setter metotları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}