package Exercises;

public class VariableNode extends ExpNode {

    double xValue;

    double value( double xValue ) {
        this.xValue = xValue;
        return xValue;
    }

    void printStackCommands() {
        System.out.println( "Push " + xValue );
    }
}
