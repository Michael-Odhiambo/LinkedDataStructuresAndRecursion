package Exercises;

public class BinarySearchTree {

    TreeNode root;  // The root of this tree.

    /**
     * Add the specified word into the tree.
     */
    TreeNode add( String word, TreeNode root  ) {
        if ( root == null ) {
            root = new TreeNode(word);
        }
        else if ( word.compareTo( root.word ) < 0 )
            root.left = add( word, root.left );
        else
            root.right = add( word, root.right );

        return root;


    }

    /**
     * Search for the given word in this binary tree.
     * @return true if the word is in this tree or false if it is not.
     */
    boolean contains( TreeNode root, String word ) {
        if ( root == null ) // The tree is empty, so it certainly does not contain this word.
            return false;
        else if ( word.equals( root.word ) )
            return true;  // The word has been found in this node.
        else if ( word.compareTo( root.word ) < 0 )  // If the word exists in this tree, then it is in the left subtree of root.
            return contains( root.left, word );
        else
            return contains( root.right, word );  // If the word exists in this tree, then it is in the right subtree of root.


    }


    /**
     * Get the size of this tree.
     */
    int size( TreeNode root ) {
        if ( root == null )
            return 0;  // The Binary Tree is empty.
        int size = 1;  // Count the root node.
        size += size( root.left );  // Count the number of nodes in the left subtree.
        size += size( root.right ); // Count the number of nodes in the right subtree.

        return size;
    }

    /**
     * An inorder traversal of the BST.
     */
    void inorderTraversal( TreeNode root ) {
        if ( root == null )
            return;
        inorderTraversal( root.left );
        TextIO.putln( root.word );
        inorderTraversal( root.right );

    }

    /**
     * Get the root node of this binary tree.
     */
    TreeNode getRoot() {
        return root;
    }
}
