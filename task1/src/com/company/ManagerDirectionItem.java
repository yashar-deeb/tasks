package com.company;

public class ManagerDirectionItem {

    static public void setPrefix(DirectionItem item, String newPrefix) {
        item.prefix = newPrefix;
    }

    static public void setNameOfDirection(DirectionItem item, String newNameOfDirection) {
        item.nameOfDirection = newNameOfDirection;
    }

    static public void setPricePerMinute(DirectionItem item, double newPricePerMinute) {
        item.pricePerMinute = newPricePerMinute;
    }

    static public String getPrefix(DirectionItem item) {
        return item.prefix;
    }

    static public String getNameOfDirection(DirectionItem item) {
        return item.nameOfDirection;
    }

    static public double getPricePerMinute(DirectionItem item) {
        return item.pricePerMinute;
    }
}
