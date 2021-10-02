package DepthBreathSearch;

/**
 * A queue of Locations, with the standard operations, plus a getSize() method that returns
 * the number of Locations on the queue.
 */
class Queue {

    private Node head = null;  // Points to the first Node in the queue.
    private Node tail = null;  // Points to the last Node in the queue.
    private int size;  // Number of items in the queue.

    /**
     * Add the specified location to the end of the queue.
     * @param loc
     */
    void enqueue( Location loc ) {
        Node newTail = new Node();
        newTail.loc = loc;
        if ( head == null ) {
            head = newTail;
            tail = newTail;
        }
        else {
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    /**
     * Remove and return the first item in the queue. ( Note that this will throw
     * a NullPointerException if the queue is empty. )
     */
    Location dequeue() {
        Location firstLocation = head.loc;
        head = head.next;

        if ( head == null )
            tail = null;

        size--;
        return firstLocation;

    }

    boolean isEmpty() {
        // Return true if the queue is empty.
        return head == null;
    }

    /**
     * Return the number of items in the queue.
     */
    int getSize() {
        return size;
    }
}
