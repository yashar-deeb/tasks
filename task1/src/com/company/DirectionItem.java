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

    public void setPrefix(DirectionItem item, String newPrefix) {
        item.prefix = newPrefix;
    }

    public void setNameOfDirection(String nameOfDirection) {
        this.nameOfDirection = nameOfDirection;
    }

    public void setPricePerMinute(double pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getNameOfDirection() {
        return this.nameOfDirection;
    }

    public double getPricePerMinute() {
        return this.pricePerMinute;
    }
}
