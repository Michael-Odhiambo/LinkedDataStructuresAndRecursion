package Parsers;

/**
 * An object of type ParseError represents a syntax error found in the user's
 * input.
 */
public class ParseError extends Exception {
    ParseError( String message ) {
        super( message );
    }

}
