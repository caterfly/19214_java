package mypackage.operations.unary;

import mypackage.operations.UnaryOperation;

public class UnaryMinus extends UnaryOperation {

    public static final String symbol = "~";

    @Override
    public double execute() {
        return -getArgument(0);
    }

}
