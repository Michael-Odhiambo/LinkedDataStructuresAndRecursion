package Exercises.ParsersRevisited;

/**
 * In this parser, an expression is defined by the following BNF rule:
 *
 *                 <expression>  ::= [ "-" ] <term> [ ( "+" | "-" ) <term> ] ...
 *                 <term>  ::=  <factor> [ ( "*" | "-" ) <factor> ] ...
 *                 <factor>  ::= [ "-" ] <number> | "(" <expression> ")"
 */

public class Parser2 {

    public static void main( String[] args ) {

        while ( true ) {

            System.out.println( "\n\nEnter an expression, or press return to end." );
            System.out.print( "\n:  " );

            TextIO.skipBlanks();

            if ( TextIO.peek() == '\n' )
                break;

            try {
                double value = expressionValue();
                TextIO.skipBlanks();

                if ( TextIO.peek() != '\n' )
                    throw new ParseError( "Extra data found at the end of the expression." );
                TextIO.getln();

                System.out.println( "\nValue is " + value );

            }
            catch ( ParseError e ) {
                System.out.println( "\n ***Error in input: " + e.getMessage() );
                System.out.println( "***Discarding input: " + TextIO.getln() );
            }
        }



    }


    /**
     * Calculates the value of the expression input by the user.
     * @return the value of the expression.
     */
    private static double expressionValue() throws ParseError {

        TextIO.skipBlanks();  // Skip past any blank spaces in the input.

        // Since an expression can have a leading minus, negative will be set to true if this is the case.
        boolean isNegative = false;

        // Look at the first character in the expression.
        if ( TextIO.peek() == '-' ) {
            isNegative = true;  // The expression has a leading negative.
            TextIO.getAnyChar();  // Read the negative to remove it from the input stream.
        }

        TextIO.skipBlanks();  // Skip any blank spaces after the negative.
        double value;  // The value of this expression.

        value = termValue();  // Get the value of the first term of the expression.

        if ( isNegative )
            value = -value;

        TextIO.skipBlanks();

        /*
        Terms in an expression are combined with "+" or "-" signs. Check for any other terms in the expression
        and apply the appropriate operation.
         */
        while ( TextIO.peek() == '+' || TextIO.peek() == '-' ) {
            char operator = TextIO.getAnyChar();  // Read the operator
            double nextValue = termValue();

            if ( operator == '+' )
                value += nextValue;  // Add the value of the next term to the value of the previous terms.
            else
                value -= nextValue;  // Subtract the value of the next term from the value of the previous terms.

            TextIO.skipBlanks();

        }

        return value;

    }

    /**
     * This method returns the value of a term in an expression.
     */
    private static double termValue() throws ParseError {

        TextIO.skipBlanks();  // Skip past any blank spaces.

        double value = factorValue();  // Get the value of the first factor in the term.

        TextIO.skipBlanks();  // Skip past any blank spaces.

        /*
        The factors in a term are combined by "*" or "/". Check for any other factors and apply the
        appropriate operation.
         */
        while ( TextIO.peek() == '*' || TextIO.peek() == '/' ) {
            char operator = TextIO.getAnyChar();  // Get the operator from the input.
            double nextValue = factorValue();

            if ( operator == '*' )
                value *= nextValue;
            else
                value /= nextValue;

            TextIO.skipBlanks();

        }
        return value;
    }

    /**
     * This method returns the value of a factor in a term.
     */
    private static double factorValue() throws ParseError {

        /*
        Since a number can be negative, "isNegative" is set to true if there is a leading minus sign
        before the number.
         */
        boolean isNegative = false;

        TextIO.skipBlanks();  // Skip past any blank spaces.
        if ( TextIO.peek() == '-' ) {
            // Read the minus.
            TextIO.getAnyChar();
            isNegative = true;
        }
        TextIO.skipBlanks();

        if ( Character.isDigit( TextIO.peek() ) ) {
            double number = TextIO.getDouble();

            if ( isNegative )
                number = -number;

            return number;

        }

        else if ( TextIO.peek() == '(' ) {
            TextIO.getAnyChar();  // Read the "(".

            // The factor is made up of an expression. So get the value of the expression.
            double value = expressionValue();

            TextIO.skipBlanks();

            // We expect a ")" at the end of the expression. If it's not there, throw an error.
            if ( TextIO.peek() != ')' )
                throw new ParseError( "No closing brackets found at the end of the expression." );
            TextIO.getAnyChar();  // Read the ")".

            return value;  // Return the value of the expression.
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
