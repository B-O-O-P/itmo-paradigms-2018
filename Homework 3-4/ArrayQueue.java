package queue;


public class ArrayQueue extends AbstractQueue {
    private int head;
    private int tail;
    private Object[] elements = new Object[5];

    //pre: element ≠ null
    /*public void enqueue(Object element) {
        assert element != null;

        size++;
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }*/
    //post: tail = (tail'+1) % n && a[tail'] = element


    protected void enqueueImpl(Object element) {
        ensureCapacity(size() + 1);
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }


    private void ensureCapacity(int capacity) {
        if (capacity < elements.length) {
            return;
        }
        int size = size();
        elements = CopyPasta(2 * capacity);
        head = 0;
        tail = size;

    }
    //post: elements immutable


    //pre: size() > 0
    /*public Object dequeue() {
        assert size() > 0;

        Object value = elements[head];
        head = (head + 1) % elements.length;
        size--;
        return value;
    }*/
    //post: R = a[head'] && head = (head'+1) % n && ∀i≠head' && i=0..n: a[i]=a[i]'

    protected void delete() {
        head = (head + 1) % elements.length;
    }

    //pre: size() > 0
    /*public Object element() {
        return elements[head];
    }*/
    //post: R = a[head] && head = head' && immutable

    protected Object elementImpl() {
        return elements[head];
    }

    /*public int size() {
        return size;
    }*/
    //post: R = n  && immutable

    /*public void clear() {
        Object[] newElements = new Object[size()];
        size = head = tail = 0;
        elements = newElements;
    }*/
    //post: n=n' && ∀i=0..n: a[i]=null && head'=0 && tail' =0

    protected void setToZero() {
        elements = new Object[size()];
        head = tail = 0;
    }

    /*public boolean isEmpty() {
        return size == 0;
    }*/
    //post:R = n = 0 && n=n' && immutable

    /*public Object[] toArray() {
        int size = size();
        return CopyPasta(size);
    }*/
    //post: elements immutable

    protected Object[] CopyPasta(int n) {
        Object[] res = new Object[n];
        for (int i = 0; i < n; i++) {
            res[i] = elements[(head + i) % (elements.length)];
        }
        return res;
    }
}
