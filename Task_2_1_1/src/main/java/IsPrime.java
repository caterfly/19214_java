import java.util.function.Predicate;

public class IsPrime implements Predicate<Integer> {

    public boolean isPrime(Integer n) {
        if (n < 2) {
            return false;
        }
        for(int i = 2; i * i < n; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;

    }

    @Override
    public boolean test(Integer n) {
        return !isPrime(n);
    }
}
