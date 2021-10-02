package BinaryTrees;

/**
 * An object of type TreeNode represents one node in a binary tree of strings.
 */
class TreeNode {

    String item;  // The data in this node.
    TreeNode left;  // Pointer to the left subtree.
    TreeNode right;  // Pointer to the right subtree.

    TreeNode(String str) {
        // Constructor. Make a node containing the specified string.
        // Note that left and right pointers are initially null.
        item = str;

    }

}
