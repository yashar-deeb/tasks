package com.company;

public class ManagerConsoleOutput {
    static void PrintPrefix(DirectionItem item) {
        System.out.println(ManagerDirectionItem.getPrefix(item));
    }

    static void PrintNameOfDirection(DirectionItem item) {
        System.out.println(ManagerDirectionItem.getNameOfDirection(item));
    }

    static void PrintPricePerMinute(DirectionItem item) {
        System.out.println(ManagerDirectionItem.getPricePerMinute(item));
    }
}
