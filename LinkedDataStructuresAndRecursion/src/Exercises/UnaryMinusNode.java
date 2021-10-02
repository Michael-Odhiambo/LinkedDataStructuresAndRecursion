package Exercises;

public class UnaryMinusNode extends ExpNode {
    ExpNode operand;  // The operand to which the unary minus is to be applied.

    /**
     * Construct the UnaryMinusNode to be applied to the specified operand.
     */
    UnaryMinusNode( ExpNode operand ) {
        assert operand != null;  // An operand must be provided.
        this.operand = operand;

    }

    /**
     * Return the value of this node.
     */
    double value( double xValue ) {
        double operandValue = operand.value( xValue );
        return -operandValue;

    }

    /**
     * To evaluate this expression on a stack machine, first do whatever is necessary to evaluate the operand, leaving
     * the operand on the stack. Then apply the unary minus ( which means popping the operand, negating it, and pushing
     * the result back onto the stack. )
     */
    void printStackCommands() {
        this.operand.printStackCommands();
        System.out.println( "Unary minus." );
    }
}
