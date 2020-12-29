package mypackage.operations;

import mypackage.Operation;

public class Logarithm extends Operation {

    @Override
    public String getOperationSymbol() {
        return "log";
    };

    @Override
    public double execute() {
        if(getArgument(0) <= 0)
            throw new ArithmeticException("Argument must be positive!");
        return Math.log(getArgument(0));
    }

    @Override
    public int getArity() {
        return 1;
    }
}
