package sample;

/*Дан список строк, состоящий из 3*n элементов. Переставить элементы исходного списка
* таким образом, чтобы новый порядок элементов был следующим
* (в квадратных скобках приведены номера первоначального списка, нумерация с 0):
*  [0], [n], [2n], [1], [n+1], [2n+1], [2], [n+2], [2n+2] и т.д. Новых объектов ListNode / ListItem – не создавать.
*  Подсказка: первоначально найти элементы [0], [n], [2n], затем синхронно продвигаясь по элементам исходного списка
*  с этих позиций формировать новый список.
* Пример: { 1, 2, 3, 44, 55, 66, 777, 888, 999 } –> { 1, 44, 777, 2, 55, 888, 3, 66, 999 }*/


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

    public MyLinkedList(MyLinkedList other) {
        ListItem item = other.head;
        while (item != null) {
            this.addLast(item.value);
            item = item.next;
        }
    }

    public MyLinkedList() {
    }


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

    public MyLinkedList<T> process() {
        MyLinkedList<T> ans = new MyLinkedList<>();
        if(this.size % 3 != 0) {
            System.out.println("Array size is not equal to 3*n");
            return ans;
        }

        int n = this.size / 3;
        int first = 0, second = n, third = 2*n;
        for(int i = 0; i < n; i++) {
            try {
                ans.addLast(this.get(first).value);
                ans.addLast(this.get(second).value);
                ans.addLast(this.get(third).value);
            } catch (Exception e) {
                e.printStackTrace();
            }
            first++;
            second++;
            third++;
        }

        return ans;
    }
}
