import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

public class PrimeThreads<T> {




    private final int nthreads;

    PrimeThreads(int nthreads) {
        answer = new Answer<T>();
        this.nthreads = nthreads;
    }


    private Iterator<T> nthThread(Iterator<T> iter, int N) {

        ArrayList<T> nth = new ArrayList<T>();

        for (int i = 0; iter.hasNext(); ++i) {
            T elem = iter.next();

            if (i % N == 0) {
                nth.add(elem);
            }
        }

        return nth.iterator();
    }

    private Answer<T> answer;

    public static class Answer<T> {

        private boolean isFound = false;
        T answ;

        Answer(){}

        synchronized void setAnswer(T answer) {

            answ = answer;
            isFound = true;
        }

        boolean isAnswerFound() {
            return isFound;
        }

    }

    public static class ThreadStuff<T> extends Thread {

        private final Iterator<T> iter;
        private final Predicate<T> pred;
        private Answer<T> answ;
        private final Thread thr;

        ThreadStuff(Iterator<T> iter, Predicate<T> pred, Answer<T> answ) {
            this.iter = iter;
            this.pred = pred;
            this.answ = answ;
            this.thr = new Thread(this);
            thr.start();
        }

        @Override
        public void run() {

            while (iter.hasNext()) {
                if (answ.isAnswerFound()) {
                    return;
                }

                T elem = iter.next();
                if(pred.test(elem)) {
                    answ.setAnswer(elem);
                }
            }
        }



    }

    Answer<T> runThreads (ArrayList<T> tasks, int nthreads, Predicate<T> predicate) {

        boolean isEnough = true;
        ArrayList<ThreadStuff<T>> handlers = new ArrayList<>();

        for(int i = 0; i < nthreads; ++i) {

            Iterator<T> iter = tasks.iterator();

            for (int j = 0; j < i; ++j) {

                if (iter.hasNext())
                    iter.next();

                else {
                    isEnough = false;
                    break;
                }
            }

            if (!isEnough) {
                break;
            }

            Iterator<T> task = nthThread(iter, nthreads);
            handlers.add(new ThreadStuff<T>(task, predicate, answer));
        }

        for (ThreadStuff<T> handler : handlers) {
            try {
                handler.thr.join();
            }
            catch (InterruptedException e) {
                System.out.println("Without any further interruption let's celebrate and SSD");
            }
        }

        return answer;
    }

}