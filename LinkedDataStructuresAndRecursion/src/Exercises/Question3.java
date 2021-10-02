package Exercises;

/**
 * Write a subroutine that will make a copy of a list, with the order of the items of the list
 * reversed. The subroutine should have a parameter of type ListNode, and it should return a value
 * of type ListNode. The original list should not be modified.
 */

public class Question3 {

    // The head of the linked list.
    private static ListNode head;

    // The head to the new reversed list.
    private static ListNode reversedListHead;

    public static void main( String[] args ) {
        // Create a linked list with the values one to ten.
        for ( int i = 0; i < 11; i++ ) {
            head = add( i, head );
        }


        // Create a new linked list that contains the items in the original list in reverse.
        reversedListHead = addElementsInReverse( head );

        printElements( head );
        System.out.println();
        printElements( reversedListHead );


    }

    /**
     * Add an item into the linked list.
     */
    private static ListNode add( int item, ListNode node ) {
        if ( node == null ) {
            // Create a new ListNode and return it.
            ListNode newNode = new ListNode();
            newNode.item = item;
            return newNode;
        }
        node.next = add( item, node.next );

        return node;

    }

    /**
     * Print the elements of the linked list.
     */
    static ListNode addElementsInReverse( ListNode head ) {

        ListNode rev = null;
        ListNode curNode = head;

        while ( curNode != null ) {
            ListNode newNode = new ListNode();
            newNode.item = curNode.item;
            newNode.next = rev;
            rev = newNode;
            curNode = curNode.next;
        }

        return rev;


    }

    /**
     * Print the elements in the linked list.
     */
    static void printElements( ListNode head ) {
        if ( head == null )
            return;
        System.out.println( head.item );
        printElements( head.next );

    }


    /**
     * Nested subclass representing a list node.
     */
    private static class ListNode {
        int item;  // An item in the list.
        ListNode next;  // Pointer to the next node in the list.
    }
}
