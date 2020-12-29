package mypackage.operations;

import mypackage.Operation;

public class SquareRoot extends Operation {

    @Override
    public String getOperationSymbol() {
        return "sqrt";
    };

    @Override
    public double execute() {
        if(getArgument(0) < 0)
            throw new ArithmeticException("Argument must be not negative!");
        return Math.sqrt(getArgument(0));
    }

    @Override
    public int getArity() {
        return 1;
    }
}