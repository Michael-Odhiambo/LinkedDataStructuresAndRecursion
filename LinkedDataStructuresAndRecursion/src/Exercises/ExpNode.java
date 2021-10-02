package Exercises;

abstract class ExpNode {

    abstract double value( double xValue );  // Get the value of node. Must be defined in a child class.
    abstract void printStackCommands();  // Print the stack commands for evaluating the subtree represented by this
    // node. Must be defined in a child class.
}
