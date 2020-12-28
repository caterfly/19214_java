import mypackage.ExpressionParser;
import org.junit.Assert;
import org.junit.Test;

public class MyTest {

    private ExpressionParser calculator = new ExpressionParser();
    private String input = "";

    @Test
    public void test_1() throws Exception {

        input = "sin + - 1 2 1";
        double answer = calculator.calculate(input);

        Assert.assertEquals(0, answer, 0);
    }

    @Test
    public void test_2() {

        input = "aba 33 33";

        try {
            calculator.calculate(input);
        } catch (Exception e) {
            return; // OK, invalid argument
        }
        Assert.fail();
    }

    @Test
    public void test_3() {

        input = "sin 2 2";

        try {
            calculator.calculate(input);
        } catch (Exception e) {
            return; // OK, too many arguments
        }
        Assert.fail();
    }
}