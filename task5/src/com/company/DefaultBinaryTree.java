package com.company;

import java.util.LinkedList;
import java.util.Queue;

public interface DefaultBinaryTree<T> {

    interface TreeNode<T> {
        T getValue();

        default TreeNode<T> getLeft() {
            return null;
        }

        default TreeNode<T> getRight() {
            return null;
        }
    }

    TreeNode<T> getRoot();

    interface Visitor<T> {

        public void visit(T value, int level);
    }

    default void byLevelVisit(Visitor<T> visitor) {
        class QueueItem {
            public DefaultBinaryTree.TreeNode<T> node;
            public int level;

            public QueueItem(DefaultBinaryTree.TreeNode<T> node, int level) {
                this.node = node;
                this.level = level;
            }
        }

        if (getRoot() == null) {
            return;
        }
        Queue<QueueItem> queue = new LinkedList<>();
        queue.add(new QueueItem(getRoot(), 0));
        while (!queue.isEmpty()) {
            QueueItem item = queue.poll();
            if (item.node.getLeft() != null) {
                queue.add(new QueueItem(item.node.getLeft(), item.level + 1));
            }
            if (item.node.getRight() != null) {
                queue.add(new QueueItem(item.node.getRight(), item.level + 1));
            }
            visitor.visit(item.node.getValue(), item.level);
        }
    }

    default String toBracketStr() {
        class Inner {
            <T> void printTo(TreeNode<T> node, StringBuilder sb) {
                if (node == null) {
                    return;
                }
                sb.append(node.getValue());
                if (node.getLeft() != null || node.getRight() != null) {
                    sb.append(" (");
                    printTo(node.getLeft(), sb);
                    if (node.getRight() != null) {
                        sb.append(", ");
                        printTo(node.getRight(), sb);
                    }
                    sb.append(")");
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        new Inner().printTo(getRoot(), sb);

        return sb.toString();
    }
}
