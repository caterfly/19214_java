package mypackage.operations.unary;

import mypackage.operations.UnaryOperation;

public class Logarithm extends UnaryOperation {

    public static final String symbol = "log";

    @Override
    public double execute() {
        if(getArgument(0) <= 0)
            throw new ArithmeticException("Argument must be positive!");
        return Math.log(getArgument(0));
    }
}
