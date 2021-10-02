package BinaryTrees;

import java.util.Scanner;

/**
 * This program demonstrates a few routines for processing binary sort trees.
 * It uses a binary sort tree of strings. The user types in strings. The user's string
 * is converted to lower case, and -- if it is not already in the tree -- it is inserted
 * into the tree. Then the number of nodes in the tree and a list of items in the tree
 * are output. The program ends when the user enters an empty string.
 */

public class BinarySortTreeDemo {

    private static BST searchTree;

    public static void main( String[] args ) {

        searchTree = new BST();

        Scanner in = new Scanner( System.in );  // For reading user's input.

        System.out.println( "This program stores strings that you enter in a binary sort " );
        System.out.println( "tree. After each item is inserted, the contents of the tree " );
        System.out.println( "are displayed. The number of nodes in the tree is also output." );
        System.out.println( "    Any string you enter will be converted to lowercase." );
        System.out.println( "Duplicate entries are ignored." );

        while ( true ) {
            // Get one string from the user, insert it into the tree,
            // and print some information about the tree. Exit if the user
            // enters an empty string. Note that all strings are coverted to
            // lower case.
            System.out.println( "\n\nEnter a string to be inserted, or pass return to end." );
            System.out.print( "?  " );
            String item;  // The user's input.
            item = in.nextLine().trim().toLowerCase();

            if ( item.length() == 0 )
                break;

            if ( searchTree.contains( searchTree.getRoot(), item ) ) {
                // Don't insert a second copy of an item that is already in the tree.
                System.out.println( "\n That item is already in the tree." );

            }
            else {
                searchTree.insert( item );  // Add user's string to the tree.
                System.out.println( "\nThe tree contains " + searchTree.countNodes( searchTree.getRoot() ) + " items." );
                System.out.println( "\nContents of the tree: \n" );
                searchTree.treeList( searchTree.getRoot() );
            }


        }
    }
}
