package com.company;

public class ManagerConsoleOutput {
    static void PrintPrefix(DirectionItem item) {
        System.out.println(item.getPrefix());
    }

    static void PrintNameOfDirection(DirectionItem item) {
        System.out.println(item.getNameOfDirection());
    }

    static void PrintPricePerMinute(DirectionItem item) {
        System.out.println(item.getPricePerMinute());
    }
}
