import java.util.Iterator;
import java.util.function.Predicate;


public class LookupThread<T> extends Thread {

    Iterator<T> iter;
    Predicate<T> cmp;
    int nthread;

    PrimeThreads.Bool isFound;

    LookupThread(Iterator<T> iter, Predicate<T> cmp, int nthread, PrimeThreads.Bool isFound) {


        this.iter = new Iterator<T>() {

            int cnt = 0;

            @Override
            public boolean hasNext() {

                while (iter.hasNext()) {

                    if (cnt % nthread == 0) {
                        return true;
                    }
                    iter.next();
                    cnt++;
                }
                return false;
            }

            @Override
            public T next() {
                if (this.hasNext()) {
                    cnt++;
                    return iter.next();
                }
                return null;
            }
        };

        this.cmp = cmp;
        this.nthread = nthread;
        this.isFound = isFound;
    }

    private boolean find() {

        while (iter.hasNext() && !isFound.getValue()) {

            T elem = iter.next();
            if (cmp.test(elem)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void run() {

        boolean res = find();

        if (res) {
            isFound.setValue(true);
        }
    }
}
