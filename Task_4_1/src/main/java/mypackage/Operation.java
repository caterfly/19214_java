package mypackage;

/**
 * Base class for operations
 * Contains arity - how many arguments are needed for function
 * Arguments - list of arguments
 * Cnt - current number of arguments ready to be processed
 */

public abstract class Operation {

    public int cnt;
    private double[] arguments = new double[getArity()];


    /**
     * Store input argument for future processing
     * @param argument numeric argument
     */
    public void setArgument(double argument) {
        arguments[cnt++] = argument;
    }

    /**
     * Get arguments for processing
     * @param id id of argument
     * @return argument to process
     */
    public double getArgument(int id) {
        return arguments[id];
    }

    /**
     * Set current number of arguments to 0
     * in order to prepare the object for next calculations
     */
    public void reset() {
        this.cnt = 0;
    }

    /**
     * Abstract method which should be implemented in child classes
     * @return double - result of execution
     */
    public abstract double execute();

    /**
     * Abstract method which should be implemented in child classes
     * @return int - arity of the operation
     */
    public abstract int getArity();

    /**
     * Abstract method which should be implemented in child classes
     * @return String - string representation of the operation
     */
    public abstract String getOperationSymbol();
}
