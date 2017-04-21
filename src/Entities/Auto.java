package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omyag on 17.03.2017.
 */

public class Auto {
    private int id;
    private String model;
    private String dataV;
    private String color;
    private int probeg;
    private int price;
    private List<Auto> autos = new ArrayList<>();

    public Auto(int id, String model, String dataV, String color, int probeg, int price) {
        this.id = id;
        this.model = model;
        this.dataV = dataV;
        this.color = color;
        this.probeg = probeg;
        this.price = price;
    }
//
//public Entities.Auto(int id, String model, String dataV, String color, int probeg, int price){
//        this(0,"model","Дата выпуска","Цвет",0,0);
//}

    public Auto(){

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDataV() {
        return dataV;
    }

    public void setDataV(String dataV) {
        this.dataV = dataV;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getProbeg() {
        return probeg;
    }

    public void setProbeg(int probeg) {
        this.probeg = probeg;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Auto> getAutos() {
        return autos;
    }

    public void setAutos(List<Auto> books) {
        this.autos = autos;
    }


    public void addBook(Auto a) {
        autos.add(a);
    }


    @Override
    public String toString() {
        return "Автомобили{" +
                "id=" + id +
                ", Модель='" + model + '\'' +
                ", Дата выпуска='" + dataV + '\'' +
                ", Цвет='" + color + '\'' +
                ", Пробег=" + probeg +
                ", Цена=" + price +
                '}';
    }

}


