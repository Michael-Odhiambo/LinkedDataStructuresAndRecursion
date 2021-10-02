package Exercises.ParsersRevisited;

/**
 * This program reads standard expressions input by the user and builds an expression tree that is used to
 * represent the expression. The program uses the expression tree to evaluate the expression and also prints
 * the stack commands that would be used to evaluate the expression on a stack machine. The expressions can use
 * positive or negative real numbers and the binary operators +, -, * and /.
 *
 * An expression is defined by the following BNF rule:
 *
 *              <expression>  ::=  [ "-" ] <term> [ [ "+" | "-" ] <term> ] ...
 *             <term>  ::=  <factor> [ [ "*" | "/" ]  <factor> ] ...
 *             <factor>  ::= [ "-" ] <number> | "(" <expression> ")"
 */

public class Parser3 {

    public static void main( String[] args ) {

        while ( true ) {
            System.out.println( "Enter the expression to be evaluated or press return to quit." );
            System.out.print( ": " );

            TextIO.skipBlanks();

            if ( TextIO.peek() == '\n' )
                break;

            try {
                ExpNode valueNode = expressionValue();
                TextIO.skipBlanks();

                if ( TextIO.peek() != '\n' )
                    throw new ParseError( "Extra data found at the end of the expression." );

                TextIO.getln();

                System.out.println( "The value of the expression is: " + valueNode.value() );
                System.out.println( "The stack commands are: " );
                valueNode.printStackCommands();

            }
            catch ( ParseError e ) {
                System.out.println( "An error occured: " + e.getMessage() );
                System.out.println( "Discarding extra input: " + TextIO.getln() );

            }

        }
        System.out.println( "Done." );

    }

    /**
     * This method returns an expression node representing the root to the expression tree that is
     * built from the expression that has been input by the user.
     */
    private static ExpNode expressionValue() throws ParseError {

        boolean isNegative;  // Set to true if this expression has a leading minus sign.
        isNegative = false;

        TextIO.skipBlanks();  // Skip past any blanks in the input.

        if ( TextIO.peek() == '-' ) {
            TextIO.getAnyChar();  // Read the minus sign.
            isNegative = true;

        }

        TextIO.skipBlanks();

        ExpNode value;  // The value of the first term in the expression.

        value = termValue();

        if ( isNegative )
            value = new UnaryMinusNode( value );

        TextIO.skipBlanks();

        // An expression is made up of several terms combined with a "+" or "-". Check for any other available terms.

        while ( TextIO.peek() == '+' || TextIO.peek() == '-' ) {

            char operator = TextIO.getAnyChar();  // Get the operator.
            ExpNode nextValue = termValue();  // Get the value of the next term.

            value = new BinaryOperatorNode( operator, value, nextValue );

            TextIO.skipBlanks();
        }

        return value;

    }

    /**
     * This method returns an ExpNode object that represents the value of the term being evaluated.
     */
    private static ExpNode termValue() throws ParseError {

        TextIO.skipBlanks();

        ExpNode value;
        value = factorValue();  // Get the value of the first factor in the term.

        TextIO.skipBlanks();

        /*
        A term is made up of one or more factors combined with a "*" or "/" operator. Check for any
        available terms and apply the appropriate operator.
         */
        while ( TextIO.peek() == '*' || TextIO.peek() == '/' ) {
            char operator = TextIO.getAnyChar();  // Read the operator.
            TextIO.skipBlanks();  // Skip past any blank spaces.

            ExpNode nextValue = factorValue();  // Get the value of the next factor.

            value = new BinaryOperatorNode( operator, value, nextValue );  // Create the appropriate node.

            TextIO.skipBlanks();

        }

        return value;
    }

    /**
     * This method returns an ExpNode object that represents the value of a factor in a term.
     */
    private static ExpNode factorValue() throws ParseError {

        TextIO.skipBlanks();

        boolean isNegative = false;  // This is set to true if the factor is a negative number.

        if ( TextIO.peek() == '-' ) {
            TextIO.getAnyChar();  // Read the '-'.
            isNegative = true;

        }
        TextIO.skipBlanks();

        char ch = TextIO.peek();
        ExpNode value;

        if ( Character.isDigit( ch ) ) {
            double number = TextIO.getDouble();  // Read the number.
            value = new ConstantNode( number );

            if ( isNegative )
                value = new UnaryMinusNode( value );

            return value;

        }
        else if ( TextIO.peek() == '(' ) {
            TextIO.getAnyChar();  // Read the '('.

            value = expressionValue();  // Get the value of the expression.

            TextIO.skipBlanks();

            if ( TextIO.peek() != ')' )
                throw new ParseError( "Missing right parenthesis." );
            TextIO.getAnyChar();  // Read the ')'.

            return value;

        }

        else if ( TextIO.peek() == '\n' )
            throw new ParseError( "End-of-line encountered in the middle of an expression. " );
        else if ( TextIO.peek() == ')' )
            throw new ParseError( "Extra right parenthesis." );
        else if ( TextIO.peek() == '+' || TextIO.peek() == '-' || TextIO.peek() == '*' || TextIO.peek() == '/' )
            throw new ParseError( "Misplaced operator." );
        else
            throw new ParseError( "Unexpected character \"" + TextIO.peek() + "\" encountered." );


    }
}
