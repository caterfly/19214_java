package mypackage.operations;

import mypackage.Operation;

public class Power extends Operation {

    @Override
    public String getOperationSymbol() {
        return "^";
    }

    @Override
    public double execute() {
        return Math.pow(getArgument(0), getArgument(1));
    }

    @Override
    public int getArity() {
        return 2;
    }
}
