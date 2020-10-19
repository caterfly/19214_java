import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;

public class Stack<Type> implements Iterable<Type> {

    private Type[] stack;
    private int counter = 0;
    private int size = 1;

    /**
     * initial stack for 1 elem size
     */
    Stack() {
        stack = (Type[])new Object[1];
        size = 1;
    }

    /**
     * @param elem push the stack
     */
    public void push(Type elem) {
        if (counter >= size) {
            resize();
        }
        stack[counter] = elem;
        counter++;
    }

    //if stack needs to be bigger
    private void resize() {
        stack = Arrays.copyOf(stack, stack.length * 2);
        size *= 2;
    }


    /**
     * @return pop out elem from stack
     */
    public Type pop() {
        if(counter == 0) {
            throw new EmptyStackException();
        }
        counter--;
        return stack[counter];
    }

    /**
     * @return number of elements in the stack
     */
    public int count() {
        return counter;
    }


    @Override
    public Iterator<Type> iterator() {

        return new Iterator<Type>() {

            private int curID = 0;

            @Override
            public boolean hasNext() {
                if (curID < counter) {
                    return true;
                }
                return false;
            }

            @Override
            public Type next() {
                return stack[curID++];
            }
        };

    }
}
