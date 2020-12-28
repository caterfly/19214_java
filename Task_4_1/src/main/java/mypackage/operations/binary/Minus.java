package mypackage.operations.binary;

import mypackage.operations.BinaryOperation;

public class Minus extends BinaryOperation {

    public static final String symbol = "-";

    @Override
    public double execute() {
        return getArgument(0) - getArgument(1);
    }
}