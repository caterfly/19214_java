import java.util.ArrayList;
import java.util.Iterator;

public class PoKrestyanski {

    public static boolean letsUseit(ArrayList<Integer> list) {

        IsPrime isPr = new IsPrime();
        Iterator<Integer> iterator = list.iterator();

        while(iterator.hasNext()) {
            if (isPr.test(iterator.next())) {
                return true;
            }
        }

        return false;
    }
}
