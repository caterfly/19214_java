package mypackage.operations;

import mypackage.Operation;

public class Sine extends Operation {

    @Override
    public String getOperationSymbol() {
        return "sin";
    };

    @Override
    public double execute() {
        return Math.sin(getArgument(0));
    }

    @Override
    public int getArity() {
        return 1;
    }
}
