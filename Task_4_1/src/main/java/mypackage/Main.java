package mypackage;

import java.util.Scanner;

/**
 * Class for console-like behaviour
 * quit - to quit application
 */
public class Main {

    public static void main(String[] args) {

        ExpressionParser calculator = new ExpressionParser();

        Scanner input = new Scanner(System.in);
        String expression;
        System.out.println("Enter the expression and press [ENTER] to calculate");
        System.out.println("Enter [quit] to exit from application");

        while (input.hasNextLine()) {
            try {
                expression = input.nextLine();
                if(expression.equals("quit"))
                    break;
                System.out.println(">> " + calculator.calculate(expression));
            } catch (Exception e) {
                System.out.println(">> Error occurred (" + e.getMessage() + ")");
            }
        }
        input.close();
    }
}