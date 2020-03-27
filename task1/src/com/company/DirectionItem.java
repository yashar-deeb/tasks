package com.company;

public class DirectionItem {
    protected String prefix;
    protected String nameOfDirection;
    protected double pricePerMinute;

    public DirectionItem(String prefix, String nameOfDirection, double pricePerMinute) {
        this.prefix = prefix;
        this.nameOfDirection = nameOfDirection;
        this.pricePerMinute = pricePerMinute;
    }
}
