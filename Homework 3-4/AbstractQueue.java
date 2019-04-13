package queue;

public abstract class AbstractQueue implements Queue {
    private int size;

    public void enqueue(Object element) {
        assert element != null;

        enqueueImpl(element);
        size++;
    }

    protected abstract void enqueueImpl(Object element);

    public Object dequeue() {
        assert size > 0;

        Object value = element();
        size--;
        delete();
        return value;
    }

    protected abstract void delete();


    public Object element() {
        assert size > 0;

        return elementImpl();
    }

    protected abstract Object elementImpl();


    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        setToZero();
        size = 0;
    }

    protected abstract void setToZero();

    public Object[] toArray() {
        int n = size;
        Object[] res = new Object[n];
        for (int i = 0; i < n; i++) {
            res[i] = dequeue();
            enqueue(res[i]);
        }
        return res;
    }

}
