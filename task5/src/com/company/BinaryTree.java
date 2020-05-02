package com.company;

import java.util.Stack;
import java.util.function.Function;

public class BinaryTree<T> implements DefaultBinaryTree<T> {

    class TreeNode<T> implements DefaultBinaryTree.TreeNode<T> {
        public T value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(T value, TreeNode left, TreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public TreeNode(T value) {
            this(value, null, null);
        }

        @Override
        public T getValue() {
            return value;
        }

        @Override
        public TreeNode getLeft() {
            return left;
        }

        @Override
        public TreeNode getRight() {
            return right;
        }
    }

    TreeNode<T> root = null;

    protected Function<String, T> fromStrFunc;
    protected Function<T, String> toStrFunc;

    public BinaryTree(Function<String, T> fromStrFunc, Function<T, String> toStrFunc) {
        this.fromStrFunc = fromStrFunc;
        this.toStrFunc = toStrFunc;
    }

    public BinaryTree(Function<String, T> fromStrFunc) {
        this(fromStrFunc, x -> x.toString());
    }

    public BinaryTree() {
        this(null);
    }

    @Override
    public TreeNode<T> getRoot() {
        return root;
    }

    public void clear() {
        root = null;
    }

    private T fromStr(String s) throws Exception {
        s = s.trim();
        if (s.length() > 0 && s.charAt(0) == '"') {
            s = s.substring(1);
        }
        if (s.length() > 0 && s.charAt(s.length() - 1) == '"') {
            s = s.substring(0, s.length() - 1);
        }
        if (fromStrFunc == null) {
            throw new Exception("Не определена функция конвертации строки в T");
        }
        return fromStrFunc.apply(s);
    }

    private class IndexWrapper {
        public int index = 0;
    }

    private void skipSpaces(String bracketStr, IndexWrapper iw) {
        while (iw.index < bracketStr.length() && Character.isWhitespace(bracketStr.charAt(iw.index))) {
            iw.index++;
        }
    }

    private T readValue(String bracketStr, IndexWrapper iw) throws Exception {
        skipSpaces(bracketStr, iw);
        if (iw.index >= bracketStr.length()) {
            return null;
        }
        int from = iw.index;
        boolean quote = bracketStr.charAt(iw.index) == '"';
        if (quote) {
            iw.index++;
        }
        while (iw.index < bracketStr.length() && (
                quote && bracketStr.charAt(iw.index) != '"' ||
                        !quote && !Character.isWhitespace(bracketStr.charAt(iw.index)) && "(),".indexOf(bracketStr.charAt(iw.index)) < 0
        )) {
            iw.index++;
        }
        if (quote && bracketStr.charAt(iw.index) == '"') {
            iw.index++;
        }
        String valueStr = bracketStr.substring(from, iw.index);
        T value = fromStr(valueStr);
        skipSpaces(bracketStr, iw);
        return value;
    }

    private TreeNode fromBracketStr(String bracketStr, IndexWrapper iw) throws Exception {
        T parentValue = readValue(bracketStr, iw);
        TreeNode parentNode = new TreeNode(parentValue);
        if (bracketStr.charAt(iw.index) == '(') {
            iw.index++;
            skipSpaces(bracketStr, iw);
            if (bracketStr.charAt(iw.index) != ',') {
                TreeNode leftNode = fromBracketStr(bracketStr, iw);
                parentNode.left = leftNode;
                skipSpaces(bracketStr, iw);
            }
            if (bracketStr.charAt(iw.index) == ',') {
                iw.index++;
                skipSpaces(bracketStr, iw);
            }
            if (bracketStr.charAt(iw.index) != ')') {
                TreeNode rightNode = fromBracketStr(bracketStr, iw);
                parentNode.right = rightNode;
                skipSpaces(bracketStr, iw);
            }
            if (bracketStr.charAt(iw.index) != ')') {
                throw new Exception(String.format("Ожидалось ')' [%d]", iw.index));
            }
            iw.index++;
        }

        return parentNode;
    }

    public void fromBracketNotation(String bracketStr) throws Exception {
        IndexWrapper iw = new IndexWrapper();
        TreeNode root = fromBracketStr(bracketStr, iw);
        if (iw.index < bracketStr.length()) {
            throw new Exception(String.format("Ожидался конец строки [%d]", iw.index));
        }
        this.root = root;
    }

    @Override
    public boolean equals(Object obj) {
        class Pair {
            T value;
            int level;

            Pair(T value, int level) {
                this.value = value;
                this.level = level;
            }

            @Override
            public boolean equals(Object obj) {
                Pair other = (Pair) obj;
                return (value == other.value && level == other.level);
            }
        }

        BinaryTree<T> tree = (BinaryTree<T>) obj;

        Stack<Pair> ourStackOfTree = new Stack<>();
        Stack<Pair> otherStackOfTree = new Stack<>();

        this.byLevelVisit(((value, level) -> ourStackOfTree.add(new Pair(value, level))));
        tree.byLevelVisit(((value, level) -> otherStackOfTree.add(new Pair(value, level))));

        return ourStackOfTree.equals(otherStackOfTree);
    }
}
