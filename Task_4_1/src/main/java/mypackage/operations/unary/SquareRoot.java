package mypackage.operations.unary;

import mypackage.operations.UnaryOperation;

public class SquareRoot extends UnaryOperation {

    public static final String symbol = "sqrt";

    @Override
    public double execute() {
        if(getArgument(0) < 0)
            throw new ArithmeticException("Argument must be not negative!");
        return Math.sqrt(getArgument(0));
    }
}