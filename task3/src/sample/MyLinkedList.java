package sample;

import java.util.Random;

public class MyLinkedList<T> {

    private class ListItem {
        public T value;
        public ListItem next;

        public ListItem(T value, ListItem next) {
            this.value = value;
            this.next = next;
        }
    }

    protected ListItem head = null;
    protected ListItem tail = null;
    protected int size = 0;

    public void addFirst(T value) {
        head = new ListItem(value, head);
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void addLast(T value) {
        ListItem temp = new ListItem(value, null);
        if (tail == null) {
            head = tail = temp;
        } else {
            tail.next = temp;
            tail = temp;
        }
        size++;
    }

    private void checkEmpty() throws Exception {
        if (head == null) {
            throw new Exception("List is empty");
        }
    }

    public T getFirst() throws Exception {
        checkEmpty();
        return head.value;
    }

    public T getLast() throws Exception {
        checkEmpty();
        return tail.value;
    }

    public ListItem get(int index) throws Exception {
        if (index < 0 || index > size - 1) {
            throw new Exception("Incorrect index");
        }
        ListItem curr = head;
        while (index != 0) {
            index--;
            curr = curr.next;
        }
        return curr;
        /*
        for (ListItem curr = head; curr != null; curr = curr.next) {
            if (index == 0) {
                return curr.value;
            }
            index--;
        }
        return null;
        */
    }

    public T getValue(int index) throws Exception {
        return this.get(index).value;
    }

    public int size() {
        return size;
    }

    public void removeFirst() throws Exception {
        checkEmpty();
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
    }

    public void removeLast() throws Exception {
        checkEmpty();
        if (size == 1) {
            head = tail = null;
        } else {
            for (ListItem curr = head; ; curr = curr.next) {
                if (curr.next.next == null) {
                    tail = curr;
                    tail.next = null;
                    break;
                }
            }
        }
        size--;
    }

    public void fillNulls(int size) {
        for (int i = 0; i < size; i++) {
            this.addFirst(null);
        }
    }

    public MyLinkedList<T> shake() {
        MyLinkedList<T> ans = new MyLinkedList<>();

        if (size != 0) {
            ans.fillNulls(size);
            for (int i = 0; i < size; i++) {
                int index;
                try {
                    do {
                        index = (new Random().nextInt(size));
                    } while (ans.get(index).value != null);
                    ans.get(index).value = this.get(i).value;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return ans;
    }
}
