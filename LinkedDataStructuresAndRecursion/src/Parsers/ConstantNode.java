package Parsers;

/**
 * Represents an expression node that holds a number.
 */
public class ConstantNode extends ExpNode {
    double number;  // The number.

    ConstantNode( double val ) {
        // Construct a ConstantNode containing the specified number.
        number = val;
    }

    double value() {
        // The value of the node is the number that it contains.
        return number;

    }

    void printStackCommands() {
        // On a stack machine, just push the number onto the stack.
        System.out.println( " Push " + number );
    }
}
