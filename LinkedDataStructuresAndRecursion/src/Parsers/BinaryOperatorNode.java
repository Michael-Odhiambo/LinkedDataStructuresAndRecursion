package Parsers;

/**
 * An expression node representing a binary operator.
 */
public class BinaryOperatorNode extends ExpNode {
    char op;  // The operator.
    ExpNode left;  // The expression for its left operand.
    ExpNode right;  // The expression for its right operand.

    BinaryOperatorNode( char op, ExpNode left, ExpNode right ) {
        // Construct a BinaryOperatorNode containing the specified data.
        assert op == '+' || op == '-' || op == '*' || op == '/';
        assert left != null && right != null;
        this.op = op;
        this.left = left;
        this.right = right;


    }

    double value() {
        // The value is obtained by evaluating the left and right operands and combining
        // the values with the operator.
        double x = left.value();
        double y = right.value();

        switch (op) {
            case '+': return x + y;
            case '-': return x - y;
            case '*': return x * y;
            case '/': return x / y;
            default: return Double.NaN;  // Bad operator!

        }
    }

    void printStackCommands() {
        /*
        To evaluate the expression on a stack machine, first do whatever is necessary to evaluate
        the left operand, leaving the answer on the stack. Then do the same thing for the second operand.
        Then apply the operator ( which means popping the operands, applying the operator, and pushing
        the result ).
         */
        left.printStackCommands();
        right.printStackCommands();
        System.out.println( "  Operator " + op );
    }
}
