package Exercises;

/**
 * If a binary tree is created by inserting items in a random order, there is a high probability that the tree is
 * approximately balance. This is an experiment to prove this claim.
 */

public class Question4 {

    private static IntTreeNode root;  // The root of this binary tree.

    public static void main( String[] args ) {

        // Create a binary tree with 1023 nodes.
        for ( int i = 1; i <= 1023; i++ ) {
            int num = ( int )( Math.random() * 1023 );  // Pick a random number between 0 and 1022
            System.out.println( num );
            root = insert( root, num );

        }

        System.out.println( "Size: " + size( root ) );
        System.out.println( "Number of leaf nodes: " + countLeafNodes( root ) );

        System.out.println( root.left.number );
        System.out.println( root.right.number );

        System.out.println( "Sum of depths: " + sumOfDepths( root, 0 ) );
        System.out.println( "Maximum Depth: " + maximumDepth( root, 0 ) );

        System.out.println( "Average depth of the leaves is : " + sumOfDepths( root, 0 ) / countLeafNodes( root ) );

    }


    /**
     * This method creates a binary search tree with the specified number of nodes.
     */
    private static IntTreeNode insert ( IntTreeNode root, int number ) {

        if ( root == null ) {
            return new IntTreeNode(number);

        }
        else if ( number < root.number ) {
            root.left = insert( root.left, number );
        }
        else if ( number >= root.number )
            root.right = insert( root.right, number );

        return root;

    }

    /**
     * This subroutine returns the maximum depth of the leaves in a binary tree.
     */
    private static int maximumDepth( IntTreeNode root, int depth ) {
        if ( root == null )
            return 0;
        else if ( isLeaf( root ) )
            return depth;

        int depth1 = maximumDepth( root.left, depth + 1 );
        int depth2 = maximumDepth( root.right, depth + 1 );


        return Math.max( depth1, depth2 );


    }


    /**
     * This method finds the sum of the depths of all the leaf nodes.
     */
    private static int sumOfDepths( IntTreeNode root, int depth ) {

        if ( root == null )
            return 0;
        else if ( isLeaf( root ) )
            return depth;
        else
            return sumOfDepths( root.left, depth + 1 ) + sumOfDepths( root.right, depth + 1 );



    }

    /**
     * Count the number of leaf nodes in a binary tree.
     */
    private static int countLeafNodes( IntTreeNode root ) {

        if ( root == null )
            return 0;
        if ( isLeaf( root ) )  // This is a leaf node.
            return 1;  // Count it.
        int count = 0;
        count += countLeafNodes( root.left );
        count += countLeafNodes( root.right );

        return count;

    }

    /**
     * This method determines if the given node is a leaf node.
     */
    private static boolean isLeaf( IntTreeNode node ) {
        if ( node.left == null && node.right == null )
            return true;
        return false;
    }

    /**
     * Get the size of this tree.
     */
    private static int size( IntTreeNode root ) {
        if ( root == null )
            return 0;  // The Binary Tree is empty.
        int size = 1;  // Count the root node.
        size += size( root.left );  // Count the number of nodes in the left subtree.
        size += size( root.right ); // Count the number of nodes in the right subtree.

        return size;
    }
}
