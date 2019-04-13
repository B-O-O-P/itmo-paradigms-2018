package queue;

public class ArrayQueueADT {
    private int head;
    private int tail;
    private Object[] elements = new Object[5];

    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;

        ensureCapacity(queue, size(queue) + 1);

        queue.elements[queue.tail] = element;
        queue.tail = (queue.tail + 1) % queue.elements.length;
    }

    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity < queue.elements.length) {
            return;
        }
        int size = size(queue);
        queue.elements = copy(queue, 2 * capacity);
        queue.head = 0;
        queue.tail = size;
    }

    public static Object dequeue(ArrayQueueADT queue) {
        assert size(queue) > 0;

        Object value = queue.elements[queue.head];
        queue.head = (queue.head + 1) % queue.elements.length;
        return value;
    }

    public static Object element(ArrayQueueADT queue) {
        return queue.elements[queue.head];
    }

    private static int size(ArrayQueueADT queue) {
        if (queue.head > queue.tail)
            return queue.elements.length - queue.head + queue.tail;
        else
            return queue.tail - queue.head;
    }

    public static void clear(ArrayQueueADT queue) {
        Object[] newElements = new Object[5];
        queue.head = queue.tail = 0;
        queue.elements = newElements;
    }

    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.head == queue.tail;
    }

    public static Object[] toArray(ArrayQueueADT queue) {
        int size = size(queue);
        return getObjects(queue, size);
    }

    private static Object[] getObjects(ArrayQueueADT queue, int size) {
        Object[] res = new Object[size];
        for (int i = 0; i < size; i++) {
            res[i] = queue.elements[(queue.head + i) % (queue.elements.length)];
        }
        return res;
    }
    //post: elements immutable

    private static Object[] copy(ArrayQueueADT queue, int size) {
        return getObjects(queue, size);
    }
}
