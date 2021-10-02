package DepthBreathSearch;

/**
 * A stack of Locations, with the standard operations, plus a getSize() method that returns the
 * number of Locations on the stack.
 */
class Stack {

    private Node top = null;  // Pointer to the top of the stack.
    private int size = 0;  // Number of items on the stack.

    void push( Location location ) {

        // Add the specified location to the top of the stack.
        Node newTop = new Node();
        newTop.loc = location;
        newTop.next = top;
        top = newTop;
        size++;

    }

    /**
     * Remove and return the top Location on the stack. ( Note that this can throw a
     * NullPointerException if it is called when the stack is empty. )
     */
    Location pop() {
        Location topLocation = top.loc;
        top = top.next;
        size--;
        return topLocation;

    }

    /**
     * Return true if the stack is empty.
     */
    boolean isEmpty() {
        return top == null;
    }

    /**
     * Return the number of Locations on the Stack.
     */
    int getSize() {
        return size;

    }

}
