package mypackage.operations.binary;

import mypackage.operations.BinaryOperation;

public class Division extends BinaryOperation {

    public static final String symbol = "/";

    @Override
    public double execute() {

        if(getArgument(1) == 0)
            throw new ArithmeticException("Division by zero!");

        return getArgument(0) / getArgument(1);
    }
}
