package ir.mahfa.multilevelexpandablerecycleviewsample;


import java.util.ArrayList;

public class Categories {
    String name;
    ArrayList<Categories> categories;
    boolean expanded = false;

    public Categories(String name, ArrayList<Categories> categories) {
        this.name = name;
        this.categories = categories;
    }
}
