import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;


public class PrimeThreads<T> {

    public boolean search(Iterable<T> list, Predicate<T> pred, int nthreads) {


        Thread[] threads = new Thread[nthreads];

        Bool isFound = new Bool();

        for (int i = 0; i < nthreads; i++) {

            Iterator<T> iter = list.iterator();

            for (int j = 0; j < i; j++) {
                iter.next();
            }

            threads[i] = new LookupThread<T>(iter, pred, nthreads, isFound);
        }

        for (int i = 0; i <  nthreads; i++) {
            threads[i].start();
        }

        for (int i = 0; i <  nthreads; i++) {

            try {
                threads[i].join();
            } catch (InterruptedException e) {
                if (isFound.getValue()) {
                    return true;
                }
                e.printStackTrace();
                throw new Error("Thread interruption happened");
            }
        }

        return isFound.getValue();
    }

    public boolean findParallels(Iterable<T> iter, Predicate<T> pred) {
        return StreamSupport.stream(iter.spliterator(), false).parallel().anyMatch(pred);
    }

    public static class Bool {

        boolean value;

        Bool() {
            this(false);
        }

        Bool(boolean value) {
            this.value = value;
        }

        public boolean getValue() {
            return value;
        }

        public void setValue(boolean value) {
            this.value = value;
        }
    }


}
