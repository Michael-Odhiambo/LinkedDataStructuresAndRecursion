package Exercises.ParsersRevisited;

/**
 * A fully parenthesized expression can be specified by the following BNF rule.
 *
 *  <expression>  ::= <number> | "(" <expression> <operator> <expression> ")".
 *  <operator>  ::= "+" | "-" | "*" | "/"
 *
 *  This class reads in a fully parenthesized expression entered by the user and evaluates
 *  it.
 */

public class Parser1 {

    public static void main( String[] args ) {

        while ( true ) {
            System.out.println( "Enter a full parenthesized expression to be solved. Or press return to end." );
            System.out.print( "?: " );

            TextIO.skipBlanks();

            if ( TextIO.peek() == '\n' )
                break;

            try {
                double val = expressionValue();
                TextIO.skipBlanks();
                if ( TextIO.peek() != '\n' )
                    throw new ParseError( "Extra data after end of expression." );

                TextIO.getln();  // Read the '\n' from the input for the next iteration of the loop.
                System.out.println( "\nValue is " + val );
            }
            catch ( ParseError e ) {
                System.out.println( "\n *** Error in input: " + e.getMessage() );
                System.out.println( "*** Discarding input: " + TextIO.getln() );
            }


        }
        System.out.println( "\n\nDone." );



    }

    /**
     * This method reads an expression entered by the user and returns its value.
     */
    private static double expressionValue() throws ParseError {

        TextIO.skipBlanks();  // Skip past any blanks.

        if ( Character.isDigit( TextIO.peek() ) )
            return TextIO.getDouble();  // The expression is made up of only a number.

        else if ( TextIO.peek() == '(' ) {
            TextIO.getAnyChar();  // Read the ')'.

            double leftOperand = expressionValue();  // Get the value of the left operand of this expression.
            char operator = getOperator();  // Get the operator.
            double rightOperand = expressionValue();  // Get the value of the right operand of this expression.

            // We expect a ')' at the end of this expression. If not, throw an error.
            TextIO.skipBlanks();

            if ( TextIO.peek() != ')' )
                throw new ParseError( "Missing right hand parenthesis." );

            TextIO.getAnyChar();  // Read the ')'.

            // Calculate the value of the expression.
            switch ( operator ) {
                case '+': return leftOperand + rightOperand;
                case '-': return leftOperand - rightOperand;
                case '*': return leftOperand * rightOperand;
                case '/': return leftOperand / rightOperand;
                default: return Double.NaN;
            }
        }
        else
            throw new ParseError( "Unknown character found: " + TextIO.getAnyChar() );

    }

    /**
     * This method reads in an operator to be applied to the operands of an expression.
     */
    private static char getOperator() throws ParseError {
        TextIO.skipBlanks();  // Skip past any blanks in the input.

        char op = TextIO.peek();

        if ( op == '+' || op == '-' || op == '*' || op == '/' )
            TextIO.getAnyChar();  // Read the operator.
        else
            throw new ParseError( "Found " + op + " instead of an operator." );

        return op;

    }
}
