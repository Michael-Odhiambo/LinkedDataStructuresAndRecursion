package Parsers;

/**
 * An abstract class representing any node in an expression tree. The three concrete node
 * classes are concrete subclasses. Two instance methods are specified, so that they can be
 * used with any ExpNode. The value() method returns the value of the expression. The
 * printStackCommands() method prints a list of commands that could be used to evaluate the
 * expression on a stack machine ( assuming that the value of the expression is to be left on
 * the stack. )
 */

abstract class ExpNode {
    abstract double value();
    abstract void printStackCommands();

}
