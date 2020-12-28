package mypackage.operations.unary;

import mypackage.operations.UnaryOperation;

public class Cosine extends UnaryOperation {

    public static final String symbol = "cos";

    @Override
    public double execute() {
        return Math.cos(getArgument(0));
    }
}
