package mypackage.operations;

import mypackage.Operation;

public class UnaryMinus extends Operation {

    @Override
    public String getOperationSymbol() {
        return "~";
    };

    @Override
    public double execute() {
        return -getArgument(0);
    }

    @Override
    public int getArity() {
        return 1;
    }

}
