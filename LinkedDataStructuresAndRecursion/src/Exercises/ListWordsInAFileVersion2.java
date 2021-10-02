package Exercises;

import java.util.ArrayList;

/**
 * Makes an alphabetical list of all the words in a file selected by the user.
 * The list can be written to a file. In this version, instead of storing the
 * words in a list, the words are stored in a Binary Sort Tree.
 */

public class ListWordsInAFileVersion2 {

    private static BinarySearchTree tree;  // The root node of the Binary Search Tree that stores the sorted words.


    public static void main( String[] args ) {

        System.out.println( "\n\nThis program will ask you to select an input file." );
        System.out.println( "It will read that file and make an alphabetical " );
        System.out.println( "list of all the words in the file. After reading " );
        System.out.println( "the file, the program asks you to select an output " );
        System.out.println( "file. If you select a file, the list of words will " );
        System.out.println( "be written to that file; if you cancel, the list will be " );
        System.out.println( "written to standard output. All words are converted " );
        System.out.println( "to lower case, and duplicates are eliminated from the list. \n\n" );

        System.out.print( "Press return to begin." );
        tree = new BinarySearchTree();
        TextIO.getln();  // Wait for the user to press return.

        try {
            if ( TextIO.readUserSelectedFile() == false ) {
                System.out.println( "No input file selected. Exiting." );
                System.exit( 1 );
            }

            String word = readNextWord();

            while ( word != null ) {
                word = word.toLowerCase();  // Convert word to lower case.

                if ( !tree.contains( tree.getRoot(), word ) ) {
                    // This is a new word, so add it to the list.
                    tree.root = tree.add( word, tree.getRoot() );

                }
                word = readNextWord();

            }
            System.out.println( "Number different words found in file: " + tree.size( tree.getRoot() ) );
            System.out.println();

            if ( tree.size( tree.getRoot() ) == 0 ) {
                System.out.println( "No words found in file." );
                System.out.println( "Exiting without saving data." );
                System.exit( 0 );

            }

            TextIO.writeUserSelectedFile();  // If the user cancels, output automatically
                                             // goes to standard output.
            TextIO.putln( tree.size( tree.getRoot() ) + " words found in file: \n " );

            tree.inorderTraversal( tree.getRoot() );
            System.out.println( "\n\nDone.\n\n" );

        }
        catch ( Exception e ) {
            System.out.println( "Sorry, an error has occurred." );
            System.out.println( "Error Message: " + e.getMessage() );

        }
        System.exit( 0 );  // Might be necessary, because of use of file dialogs.
    }


    /**
     * Read the next word from TextIO, if there is one. First, skip past any non-letters in the
     * input. If an end-of-file is encountered before a word is found, return null. Otherwise, read
     * and return the word. A word is defined as a sequence of letters. Also, a word can include an
     * apostrophe if the apostrophe is surrounded by letters on each side.
     * @return the next word from TextIO, or null if an end-of-file is encountered.
     */
    private static String readNextWord() {

        char ch = TextIO.peek();  // Look at the next character in input.
        while ( ch != TextIO.EOF && ! Character.isLetter( ch ) ) {
            // Skip past non-letters.
            TextIO.getAnyChar();  // Read the character.
            ch = TextIO.peek();  // Look at the next character.

        }
        if ( ch == TextIO.EOF )  // Encountered an end-of-file.
            return null;

        // At this point, we know that the next character is a letter, so read a word.
        String word = "";  // This will be the word that is read.

        while ( true ) {
            word += TextIO.getAnyChar();  // Append the letter onto word.
            ch = TextIO.peek();  // Look at the next character.

            if ( ch == '\'' ) {
                /*
                The next character is an apostrophe. Read it, and if the following character is
                a letter, add both the apostrophe and the letter onto the word and continue reading
                the word. If the character after the apostrophe is not a letter, the word is done, so
                break out of the loop.
                 */
                TextIO.getAnyChar();  // Read the apostrophe.
                ch = TextIO.peek();   // Look at char that follows apostrophe.

                if ( Character.isLetter( ch ) ) {
                    word += "\'" + TextIO.getAnyChar();
                    ch = TextIO.peek();
                }
                else
                    break;

            }
            if ( ! Character.isLetter( ch ) )  // If the next character is not a letter, the word is finished, so break out of the loop.
                break;


        }
        return word;  // Return the word that has been read.
    }
}
