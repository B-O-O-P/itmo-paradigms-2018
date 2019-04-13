package queue;

//pre: n>=0 && ∀0..n-1: a[i]≠null
public class ArrayQueueModule {
    private static int head;
    private static int tail;
    private static Object[] elements = new Object[5];

    //pre: element ≠ null
    public static void enqueue(Object element) {
        assert element != null;

        ensureCapacity(size() + 1);

        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }
    //post: tail = (tail'+1) % n && a[tail'] = element

    private static void ensureCapacity(int capacity) {
        if (capacity < elements.length) {
            return;
        }
        int size = size();
        elements = copy(2 * capacity);
        tail = size;
        head = 0;
    }

    //pre: size() > 0
    public static Object dequeue() {
        assert size() > 0;

        Object value = elements[head];
        head = (head + 1) % elements.length;
        return value;
    }
    //post: R = a[head'] && head = (head'+1) % n && ∀i≠head' && i=0..n: a[i]=a[i]'

    //pre: size() > 0
    public static Object element() {
        return elements[head];
    }
    //post: R = a[head] && head = head' && immutable


    public static int size() {
        if (head > tail)
            return elements.length - head + tail;
        else
            return tail - head;
    }
    //post: R = n  && immutable

    public static void clear() {
        Object[] newElements = new Object[size()];
        head = tail = 0;
        elements = newElements;
    }
    //post: n=n' && ∀i=0..n: a[i]=null && head'=0 && tail' =0


    public static boolean isEmpty() {
        return head == tail;
    }
    //post:R = n = 0 && n=n' && immutable

    public static Object[] toArray() {
        int size = size();
        return getObjects(size);
    }
    //post: elements immutable


    private static Object[] copy(int size) {
        return getObjects(size);
    }

    private static Object[] getObjects(int size) {
        Object[] res = new Object[size];
        for (int i = 0; i < size; i++) {
            res[i] = elements[(head + i) % (elements.length)];
        }
        return res;
    }
}
