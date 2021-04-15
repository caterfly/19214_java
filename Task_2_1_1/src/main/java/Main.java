import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Predicate<Integer> pred = new IsPrime();

        ArrayList<Integer> numbers = new ArrayList<>();
        File file = new File("MyFile.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String text;
            while ((text = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(text));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean res;
        long start;
        long duration;


        //linear search
        start = System.nanoTime();
        res = PoKrestyanski.letsUseit(numbers);
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(res);
        System.out.println("Time spent: " + duration + " ms" + "\n\n");



        //using threads
        int nthreads = 4;
        PrimeThreads<Integer> threadsHandler = new PrimeThreads<>(nthreads);
        start = System.nanoTime();
        PrimeThreads.Answer<Integer> answer = threadsHandler.runThreads(numbers, nthreads, pred);
        duration = (System.nanoTime() - start) / 1_000_000;
        res = answer.isAnswerFound();
        System.out.println(res);
        System.out.println("Time spent: " + duration +  " ms" + "\n\n");



        //using Parallel Stream
        //System.out.println("parallelStream");
        start = System.nanoTime();
        Stream<Integer> parStream = numbers.parallelStream();
        res = parStream.anyMatch(pred);
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(res);
        System.out.println("Time spent: " + duration +  " ms");
    }

}