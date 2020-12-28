package mypackage;

import mypackage.operations.Operation;
import mypackage.operations.binary.*;
import mypackage.operations.unary.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that contains a set of objects -
 * operations for expression evaluation
 */
class OperationMap {

    /**
     * HashMap - for faster match searching
     */
    private static final Map<String, Operation> OperationMap = new HashMap<>();

    static {
        OperationMap.put(Division.symbol, new Division());
        OperationMap.put(Multiplication.symbol, new Multiplication());
        OperationMap.put(Minus.symbol, new Minus());
        OperationMap.put(Plus.symbol, new Plus());
        OperationMap.put(Power.symbol, new Power());

        OperationMap.put(Cosine.symbol, new Cosine());
        OperationMap.put(Logarithm.symbol, new Logarithm());
        OperationMap.put(Sine.symbol, new Sine());
        OperationMap.put(SquareRoot.symbol, new SquareRoot());
        OperationMap.put(UnaryMinus.symbol, new UnaryMinus());
    }

    static final Map<String, Operation> Operations = Collections.unmodifiableMap(OperationMap);
}
