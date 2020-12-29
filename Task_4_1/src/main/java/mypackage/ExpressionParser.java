package mypackage;

import mypackage.operations.*;

import java.util.HashMap;
import java.util.Map;

public class ExpressionParser {

    private MyStack<Double> stack = new MyStack();

    /**
     * Public wrapper method for "process" function
     * The main class function needed to calculate the expression expr
     * @param expr Expression to evaluate in prefix form (e.g. cos - 4 5)
     * @return Answer - calculation result
     * @throws Exception according to process function
     */
    public double calculate(String expr) throws Exception {

        stack.clear();
        String[] tokens = tokenize(expr);
        int length = tokens.length;

        for(int i = length - 1; i >= 0; i--) {
            process(tokens[i]);
        }

        if(stack.count() > 1)
            throw new IllegalArgumentException("Too many arguments!");

        return stack.pop();
    }

    private final Map<String, Operation> OperationMap;


    public ExpressionParser() {
        OperationMap = new HashMap<>();

        addOperator(new Division());
        addOperator(new Minus());
        addOperator(new Multiplication());
        addOperator(new Plus());
        addOperator(new Power());
        addOperator(new Cosine());
        addOperator(new Logarithm());
        addOperator(new Sine());
        addOperator(new SquareRoot());
        addOperator(new UnaryMinus());
    }

    private void addOperator(Operation command) {
        if (command == null) {
            throw new IllegalArgumentException("command is null");
        }
        OperationMap.put(command.getOperationSymbol(), command);
    }

    /**
     * Method for parsing and evaluating input tokens
     * @param token part of expression (e.g. operand, operation)
     * @throws Exception (e.g. Not enough arguments, Invalid token)
     */
    private void process(String token) throws Exception {


        try {
            if(!OperationMap.containsKey(token)) {
                double argument = Double.parseDouble(token);
                stack.push(argument);
            } else {

                Operation op = OperationMap.get(token);
                op.reset();

                try {
                    if (op.getArity() == 2) {
                        op.setArgument(stack.pop());
                        op.setArgument(stack.pop());
                    } else {
                        op.setArgument(stack.pop());
                    }
                } catch (Exception e) {
                    throw new Exception("Not enough arguments!");
                }

                if(op.getArity() == op.cnt) {
                    stack.push(op.execute());
                }
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid token found: \"" + token + "\"");
        }

    }

    /**
     * Method for splitting an expression into parts - tokens
     * @param input expression to tokenize
     * @return array with tokens
     */
    private String[] tokenize(String input) {
        return input.split("\\s+");
    }

}