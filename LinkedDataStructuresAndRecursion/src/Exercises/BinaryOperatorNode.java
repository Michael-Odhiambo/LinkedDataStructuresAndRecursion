package Exercises;

public class BinaryOperatorNode extends ExpNode {

    char op;   // The operator.
    ExpNode left;  // The expression for its left operand.
    ExpNode right;  // The expression for its right operand.

    BinaryOperatorNode(char op, ExpNode left, ExpNode right ) {
        this.op = op;
        this.left = left;
        this.right = right;

    }

    /**
     * The value of this node is obtained by evaluating the left and right operands and combining the
     * values with the operator.
     */
    double value( double xValue ) {
        double x = left.value( xValue );
        double y = right.value( xValue );

        switch( op ) {
            case '+': return x + y;
            case '-': return x - y;
            case '*': return x * y;
            case '/': return x / y;
            default: return Double.NaN;  // Bad operator. Not actually possible but is required by java syntax.
        }
    }

    /**
     * To evaluate the expression on a stack machine, first do whatever is necessary to evaluate the left operand,
     * leaving the answer on the stack. Then do the same this for the right operand. Then apply the operator ( which
     * means popping the operands, applying the operator,a and pushing the result back onto the stack. )
     */
    void printStackCommands() {
        left.printStackCommands();
        right.printStackCommands();
        System.out.println( "Operator " + op );

    }
}
