package Exercises.ParsersRevisited;

/**
 * This class represents a node that holds a number in an expression tree.
 */

public class ConstantNode extends ExpNode {

    double number;  // The number in this node.

    ConstantNode( Double number ) {
        // Construct the node which will contain the specified number.
        this.number = number;
    }

    /**
     * Returns the value of this node, which is just the number it contains.
     */
    double value() {
        return this.number;
    }

    /**
     * Print the stack commands required to evaluate this node. On a stack machine, just push the
     * number contained in this node onto the stack.
     */
    void printStackCommands() {
        System.out.println( "Push " + this.number );
    }
}
