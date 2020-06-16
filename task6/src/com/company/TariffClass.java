package com.company;

import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

public class TariffClass {

    private ManagerListDirectionItem manager = new ManagerListDirectionItem();

    public ManagerListDirectionItem getManager() {
        return manager;
    }

    public TariffClass() {
    }

    private String findLongestPrefix(String number) {
        int maxLengthOfPrefix = 0;
        String longestPrefix = "";
        if(manager.getSize() < number.length()) {
            for (int i = 0; i < manager.getSize(); i++) {
                Iterator<Map.Entry<String, DirectionItem>> it = manager.getEntrySet().iterator();
                StringBuilder regex = new StringBuilder();
                String prefix = it.next().getKey();
                regex.append("^");
                regex.append(prefix);
                regex.append(".+");
                if (Pattern.matches(regex.toString(), number) && prefix.length() > maxLengthOfPrefix) {
                    maxLengthOfPrefix = prefix.length();
                    longestPrefix = prefix;
                }
            }
        } else {
            for(int i = number.length() - 1; i > -1; i--) {
                String subStr = number.substring(0, i + 1);
                if(manager.containsKey(subStr) && subStr.length() > maxLengthOfPrefix) {
                    maxLengthOfPrefix = subStr.length();
                    longestPrefix = subStr;
                }
            }
        }

        return longestPrefix;
    }

    public double calcCostOfCall(String number, int durationInSeconds) {
        double cost = 0;
        String longestPrefix = findLongestPrefix(number);

        int durationInMinute = (durationInSeconds / 60);
        if (durationInSeconds / 60 < 1) {
            if (!(durationInSeconds % 60 < 6)) durationInMinute++;
        } else {
            if (durationInSeconds % 60 > 0) durationInMinute++;
        }

        try {
            cost = durationInMinute * manager.getItem(longestPrefix).getPricePerMinute();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no prefix code suitable for this number");
        }

        return cost;
    }

}
