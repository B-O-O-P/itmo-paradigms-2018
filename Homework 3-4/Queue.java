package queue;

public interface Queue {
    //pre:element ≠ null
    void enqueue(Object element);
    //post:size = size' + 1 && tail=tail'.next && tail'.value = element

    //pre:size() > 0
    Object dequeue();
    //post: R = head'.value && head = head'.next && size = size' - 1


    //pre:size > 0;
    Object element();
    //pre: R = head.value && immutable

    int size();
    //post: R = size && immutable


    boolean isEmpty();
    //post: R = size = 0 && size=size' && immutable

    void clear();
    //post: head = tail = null && size = 0

    Object[] toArray();
    //post: immutable
}
