package Parsers;

/**
 * This program reads standard expressions typed in by the user. The program constructs an expression
 * tree to represent the expression. It then prints the value of the tree. It also uses the tree to print
 * out a list of commands that could be used on a stack machine to evaluate the expression. The expressions
 * can use positive real numbers and the binary operators +, -, *, and /.
 * The unary minus operation is supported. The expressions are defined by the BNF rules:
 *
 *          <expression>  ::=  [ "-" ] <term> [ [ "+" | "-" ] <term> ] ...
 *          <term>  ::=  <factor> [ [ "*" | "/" ]  <factor> ] ...
 *          <factor>  ::=  <number> | "(" <expression> ")"
 *
 * A number must begin with a digit ( i.e., not a decimal point ). A line of input must contain
 * exactly one such expression. If extra data is found on a line after an expression has been
 * read, it is considered an error.
 */

public class Parser3 {

    public static void main( String[] args ) {

        while (true) {
            System.out.println("\n\nEnter an expression, or press return to end.");
            System.out.print("\n?  ");
            TextIO.skipBlanks();
            if ( TextIO.peek() == '\n' )
                break;
            try {
                ExpNode exp = expressionTree();
                TextIO.skipBlanks();
                if ( TextIO.peek() != '\n' )
                    throw new ParseError("Extra data after end of expression.");
                TextIO.getln();
                System.out.println("\nValue is " + exp.value());
                System.out.println("\nOrder of postfix evaluation is:\n");
                exp.printStackCommands();
            }
            catch (ParseError e) {
                System.out.println("\n*** Error in input:    " + e.getMessage());
                System.out.println("*** Discarding input:  " + TextIO.getln());
            }
        }

        System.out.println("\n\nDone.");

    } // end main()

    /**
     * Reads an expression from the current line of input and builds an expression tree that represents
     * the expression.
     * @return an ExpNode which is a pointer to the root node of the expression tree.
     * @throws ParseError if a syntax error is found in the input.
     */
    private static ExpNode expressionTree() throws ParseError {

        TextIO.skipBlanks();
        boolean negative;  // True if there is a leading minus sign.
        negative = false;

        if ( TextIO.peek() == '-' ) {
            TextIO.getAnyChar();  // Read the minus sign.
            negative = true;

        }
        ExpNode exp;  // The expression tree for the expression.
        exp = termTree();  // Start with the first term.

        if ( negative )
            exp = new UnaryMinusNode( exp );

        TextIO.skipBlanks();

        while ( TextIO.peek() == '+' || TextIO.peek() == '-' ) {
            // Read the next term and combine it with the previous terms into a bigger
            // expression tree.
            char op = TextIO.getAnyChar();
            ExpNode nextTerm = termTree();
            exp = new BinaryOperatorNode( op, exp, nextTerm );
            TextIO.skipBlanks();

        }
        return exp;
    }

    /**
     * Reads a term from the current line of input and builds an expression tree
     * that represents the expression.
     * @return an ExpNode which is a pointer to the root node of the expression tree.
     * @throws ParseError if a syntax error is found in the input.
     */
    private static ExpNode termTree() throws ParseError {

        TextIO.skipBlanks();
        ExpNode term;  // The expression tree representing the term.
        term = factorTree();
        TextIO.skipBlanks();

        while ( TextIO.peek() == '*' || TextIO.peek() == '/' ) {
            // Read the next factor, and combine it with the previous
            // factors into a bigger expression tree.
            char op = TextIO.getAnyChar();
            ExpNode nextFactor = factorTree();
            term = new BinaryOperatorNode( op, term, nextFactor );
            TextIO.skipBlanks();

        }
        return term;
    }


    /**
     * Reads a factor from the current line of input and builds an expression tree that represents the
     * expression.
     * @return an ExpNode which is a pointer to the root node of the expression tree.
     * @throws ParseError if a syntax error is found in the input.
     */
    private static ExpNode factorTree() throws ParseError {

        TextIO.skipBlanks();
        char ch = TextIO.peek();

        if ( Character.isDigit( ch ) ) {
            // The factor is a number. Return a ConstantNode.
            double num = TextIO.getDouble();
            return new ConstantNode( num );

        }
        else if ( ch == '(' ) {
            // The factor is an expression in parentheses.
            // Return a tree representing that expression.
            TextIO.getAnyChar();  // Read the "(".
            ExpNode exp = expressionTree();
            TextIO.skipBlanks();

            if ( TextIO.peek() != ')' )
                throw new ParseError( "Missing right parenthesis." );
            TextIO.getAnyChar();  // Read the ")".
            return exp;
        }
        else if ( ch == '\n' )
            throw new ParseError( "End-of-line encountered in the middle of an expression." );
        else if ( ch == ')' )
            throw new ParseError( "Extra right parenthesis." );
        else if ( ch == '+' || ch == '-' || ch == '*' || ch == '/' )
            throw new ParseError( "Misplaced operator." );
        else
            throw new ParseError( "Unexpected character \"" + ch + "\" encountered." );
    }
}
