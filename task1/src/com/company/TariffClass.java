package com.company;

import java.util.regex.Pattern;

public class TariffClass {

    private ManagerListDirectionItem manager = new ManagerListDirectionItem();

    public ManagerListDirectionItem getManager() {
        return manager;
    }

    public TariffClass() {
    }

    private int findIndexOfLongestPrefix(String number) {
        int maxLengthOfPrefix = 0, indexOfLongestPrefix = -1;

        for (int i = 0; i < manager.getSize(); i++) {
            StringBuilder regex = new StringBuilder();
            String prefix = ManagerDirectionItem.getPrefix(manager.getItem(i));
            regex.append("^");
            regex.append(prefix);
            regex.append(".+");
            if (Pattern.matches(regex.toString(), number) && prefix.length() > maxLengthOfPrefix) {
                maxLengthOfPrefix = prefix.length();
                indexOfLongestPrefix = i;
            }
        }

        return indexOfLongestPrefix;
    }

    public double calcCostOfCall(String number, int durationInSeconds) {
        double cost = 0;
        int indexOfLongestPrefix = findIndexOfLongestPrefix(number);

        int durationInMinute = (durationInSeconds / 60);
        if (durationInSeconds / 60 < 1) {
            if (!(durationInSeconds % 60 < 6)) durationInMinute++;
        } else {
            if (durationInSeconds % 60 > 0) durationInMinute++;
        }

        try {
            cost = durationInMinute * ManagerDirectionItem.getPricePerMinute(manager.getItem(indexOfLongestPrefix));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no prefix code suitable for this number");
        }

        return cost;
    }

}
