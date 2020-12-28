package mypackage.operations.unary;

import mypackage.operations.UnaryOperation;

public class Sine extends UnaryOperation {

    public static final String symbol = "sin";

    @Override
    public double execute() {
        return Math.sin(getArgument(0));
    }
}
