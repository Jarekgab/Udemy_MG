package pl.nauka.jarek.udemy_mg.model;

import android.graphics.Color;

public class ShoppingListElement {

    private String shoppingListName;
    private String name;
    private int color;
    private Boolean checked;

    private int red = Color.RED;
    private int black = Color.BLACK;

    public ShoppingListElement(String shoppingListName, String name, int color, Boolean checked){
        this.shoppingListName = shoppingListName;
        this.name = name;
        this.color = color;
        this.checked = checked;
    }

    public String getShoppingListName() {
        return shoppingListName;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public void setClassChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getClassChecked() {
        return checked;
    }

    public void setRed() {
        this.color = red;
    }

    public void setBlack() {
        this.color = black;
    }

}
