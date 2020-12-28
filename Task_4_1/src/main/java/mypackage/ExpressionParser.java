package mypackage;

import mypackage.operations.Operation;

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

    /**
     * Method for parsing and evaluating input tokens
     * @param token part of expression (e.g. operand, operation)
     * @throws Exception (e.g. Not enough arguments, Invalid token)
     */
    private void process(String token) throws Exception {

        try {
            if(!OperationMap.Operations.containsKey(token)) {
                double argument = Double.parseDouble(token);
                stack.push(argument);
            } else {

                Operation op = OperationMap.Operations.get(token);
                op.reset();

                try {
                    if (op.arity == 2) {
                        op.setArgument(stack.pop());
                        op.setArgument(stack.pop());
                    } else {
                        op.setArgument(stack.pop());
                    }
                } catch (Exception e) {
                    throw new Exception("Not enough arguments!");
                }

                if(op.readyToCalculate()) {
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