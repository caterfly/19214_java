import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackTest {
    @Test
    public void myTest() {

        Stack<Integer> stack = new Stack<Integer>();

        stack.push(96);
        stack.push(-322);
        stack.push(666);
        stack.push(228);
        stack.push(100500);

        checkCount(stack, 5);
        checkIntegerStack(stack, new Integer[]{96, -322, 666, 228, 100500});


        Stack<String> strStack = new Stack<String>();

        strStack.push("You are");
        strStack.push("amazing");
        strStack.push("just");
        strStack.push("smile :)");
        checkCount(strStack, 4);
        checkStringStack(strStack, new String[]{"You are", "amazing", "just", "smile :)"});


        //one more case
        Stack<Integer> moreStack = new Stack<Integer>();

        for(int i = 0; i < 100500; i++){
            moreStack.push(i);
        }

        checkCount(moreStack, 100500);

        for(int i = 100500 - 1; i >= 0; i--) {
            assertEquals(Integer.valueOf(i), moreStack.pop());
        }
    }


    private void checkStringStack(Stack<String> stack, String[] testStr) {
        int i = 0;
        for (String it : stack) {
            assertEquals(it, testStr[i]);
            i++;
        }
    }

    private void checkIntegerStack(Stack<Integer> stack, Integer[] testArr) {
        int i = 0;
        for (Integer it : stack) {
            assertEquals(it, testArr[i]);
            i++;
        }
    }

    private void checkCount(Stack stack, int cnt) {
        assertEquals(stack.count(), cnt);
    }

}