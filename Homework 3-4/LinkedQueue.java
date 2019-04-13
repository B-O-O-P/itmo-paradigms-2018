package queue;


public class LinkedQueue extends AbstractQueue {
    private Node head;
    private Node tail;


    protected void enqueueImpl(Object element) {
        Node oldtail = tail;
        tail = new Node(element, null);
        if (isEmpty()) {
            head = tail;
        } else {
            oldtail.next = tail;
        }
    }


    protected void delete() {
        head = head.next;
        if (isEmpty()) tail = null;
    }

    protected Object elementImpl() {
        return head.value;
    }

    protected void setToZero() {
        head = tail = null;
    }

    private class Node {
        private Object value;
        private Node next;

        public Node(Object value, Node next) {
            assert value != null;
            this.value = value;
            this.next = next;
        }
    }

}
