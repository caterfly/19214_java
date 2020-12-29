package mypackage.operations;

import mypackage.Operation;

public class Division extends Operation {

    @Override
    public String getOperationSymbol() {
        return "/";
    }

    @Override
    public int getArity() {
        return 2;
    }
    @Override
    public double execute() {

        if(getArgument(1) == 0)
            throw new ArithmeticException("Division by zero!");

        return getArgument(0) / getArgument(1);
    }
}
