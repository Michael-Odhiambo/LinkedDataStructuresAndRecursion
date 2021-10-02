package Exercises.ParsersRevisited;

/**
 * This is an abstract class that represents an expression node in an expression tree.
 */
public abstract class ExpNode {
    abstract double value();  // Get the value of node. Must be defined in a child class.
    abstract void printStackCommands();  // Print the stack commands for evaluating the subtree represented by this
                                           // node. Must be defined in a child class.

}
