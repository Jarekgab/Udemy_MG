package pl.nauka.jarek.udemy_mg.model;

import pl.nauka.jarek.udemy_mg.R;

public class NameColor {

    String name;
    int color;

    int red = R.color.red;
    int blac = R.color.black;

    public NameColor(String name, int color){
        this.name = name;
        this.color = color;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setBlac(int blac) {
        this.blac = blac;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public int getRed() {
        return red;
    }

    public int getBlac() {
        return blac;
    }

}
