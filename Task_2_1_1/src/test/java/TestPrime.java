import java.io.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestPrime {
    @Test
    public void mainCheck() throws IOException {
        int nthreads = 4;
        PrimeThreads<Integer> threadsHandler = new PrimeThreads<>();
        IsPrime isPrime = new IsPrime();

        ArrayList<Integer> numbers = bufferReader("MyFile.txt");

        long timeStart = System.currentTimeMillis();
        boolean res = PoKrestyanski.letsUseit(numbers);
        long timeEnd = System.currentTimeMillis();
        long duration = timeEnd - timeStart;
        assertFalse(res);
        System.out.println("Time spent for basic method: " + duration + " ms" + "\n\n");


        //using threads

        for (int i = 1; i <= nthreads; i++) {
            timeStart = System.currentTimeMillis();
            threadsHandler.search(numbers, isPrime, i);
            timeEnd = System.currentTimeMillis();

            System.out.printf("Time executed for %d threads is: %d ms\n", i, timeEnd - timeStart);
        }
        //using Parallel Stream
        //System.out.println("parallelStream");

        timeStart = System.currentTimeMillis();
        res = threadsHandler.findParallels(numbers, isPrime);
        timeEnd = System.currentTimeMillis();
        duration = timeEnd - timeStart;
        System.out.println(res);
        System.out.println("Time spent for Parallels: " + duration +  " ms");

    }

    private ArrayList<Integer> bufferReader(String fileName) throws IOException {

        ArrayList<Integer> numbers = new ArrayList<>();
        File file = new File(fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String text;
            while ((text = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(text));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }

}
