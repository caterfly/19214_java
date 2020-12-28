package mypackage.operations.binary;

import mypackage.operations.BinaryOperation;

public class Power extends BinaryOperation {

    public static final String symbol = "^";

    @Override
    public double execute() {
        return Math.pow(getArgument(0), getArgument(1));
    }
}
