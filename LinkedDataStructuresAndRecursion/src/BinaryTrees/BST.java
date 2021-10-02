package BinaryTrees;

/**
 * This class represents a Binary Sort Tree.
 */

public class BST {

    TreeNode root;  // Represents the root of this binary tree.

    /**
     * Add the item to the binary sort tree. (
     */
    void insert( String newItem ) {

        if ( root == null ) {
            // The tree is empty. Set root to point to a new node containing the new
            // item. This becomes the only node in the tree.
            root = new TreeNode( newItem );
            return;
        }
        TreeNode curNode = root;  // Start at the root.

        while ( true ) {
            if ( newItem.compareTo( curNode.item ) < 0 ) {
                // Since the new item is less than the item in curNode,
                // it belongs in the left subtree of curNode. If there is an
                // open space at curNode.left, add a new node there. Otherwise,
                // advance curNode down one level to the left.
                if ( curNode.left == null ) {
                    curNode.left = new TreeNode( newItem );
                    return;  // New item has been added to the tree.
                }
                else
                    curNode = curNode.left;
            }
            else {
                // Since the new item is greater than or equal to the item in curNode,
                // it belongs in the right subtree of curNode. If there is an open space
                // curNode.right, add a new node there. Otherwise, advance curNode one
                // level to the right.
                if ( curNode.right == null ) {
                    curNode.right = new TreeNode( newItem );
                    return;  // New item has been added to the tree.
                }
                else
                    curNode = curNode.right;
            }
        }
    }

    /**
     * Get the root node of this BST.
     */
    TreeNode getRoot() {
        return root;
    }

    /**
     * Return true if the given item is in the binary sort tree. Return false
     * if not.
     */
    boolean contains( TreeNode root, String item ) {
        if ( root == null ) {
            // Tree is empty, so it certainly doesn't contain item.
            return false;

        }
        else if ( item.equals( root.item ) ) {
            // Yes, the item has been found in the root node.
            return true;
        }
        else if ( item.compareTo( root.item ) < 0 ) {
            // If the item occurs, it must be in the left subtree.
            return contains( root.left, item );
        }
        else {
            // If the item occurs, it must be in the right subtree.
            return contains( root.right, item );
        }
    }

    /**
     * Print the items in the tree inorder, one item to a line.
     * Since the tree is a sort tree, the output will be in increasing order.
     */
    void treeList( TreeNode node ) {
        if ( node != null ) {
            treeList( node.left );  // Print the items in the left subtree.
            System.out.println( "  " + node.item );  // Print the item in "node".
            treeList( node.right );  // Print the items in the right subtree.
        }
    }

    /**
     * Count the nodes in the binary tree.
     */
    int countNodes( TreeNode node ) {
        if ( node == null ) {
            // Tree is empty, so it contains no nodes.
            return 0;
        }
        else {
            // Add up the root node and the nodes in its two subtrees.
            int leftCount = countNodes( node.left );
            int rightCount = countNodes( node.right );
            return 1 + leftCount + rightCount;

        }
    }
}
