package mypackage.operations;

import mypackage.Operation;

public class Cosine extends Operation {

    @Override
    public String getOperationSymbol() {
        return "cos";
    };

    @Override
    public double execute() {
        return Math.cos(getArgument(0));
    }

    @Override
    public int getArity() {
        return 1;
    }
}
