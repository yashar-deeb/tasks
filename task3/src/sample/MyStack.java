package sample;


import java.util.ArrayList;
import java.util.List;

public class MyStack<T> {
    private MyLinkedList<T> list;

    public MyStack() {
        list = new MyLinkedList<>();
    }

    public T top() throws Exception {
        return list.getFirst();
    }

    public T pop() throws Exception {
        T value = list.getFirst();
        list.removeFirst();
        return value;
    }

    public void push(T value) {
        list.addFirst(value);
    }

    public void clear() throws Exception {
        while (list.size() > 0) {
            list.removeFirst();
        }
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    MyStack getReversedStack() throws Exception {
        MyStack<T> supStack = new MyStack<>();
        MyStack ans = new MyStack();

        while (!this.isEmpty()) {
            supStack.push(this.pop());
            ans.push(supStack.top());
        }
        while (!supStack.isEmpty()) {
            this.push(supStack.pop());
        }

        return ans;
    }
}
