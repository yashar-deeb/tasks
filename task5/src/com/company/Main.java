package com.company;

public class Main {

    public static void main(String[] args) throws Exception {
        BinaryTree<Integer> first = new BinaryTree<>(s -> Integer.parseInt(s));
        BinaryTree<Integer> second = new BinaryTree<>(s -> Integer.parseInt(s));

        first.fromBracketNotation("8 (6 (4 (5), 6), 5 (, 5 (2, 8)))");
        second.fromBracketNotation("8 (6 (4 (5), 6), 5 (, 5 (2, 8)))");
        System.out.println(first.equals(second));

        first.fromBracketNotation("8 (6 (4 (7), 6), 5 (, 5 (2, 8)))");
        second.fromBracketNotation("8 (6 (4 (5), 6), 5 (, 5 (2, 8)))");
        System.out.println(first.equals(second));

        first.fromBracketNotation("8 (6 (4 (7), 6), 5 (, 5 (2, 8)))");
        second.fromBracketNotation("8 (6 (4 (7), 6), 5 (, 5 (2, 8)))");
        System.out.println(first.equals(second));
    }
}
