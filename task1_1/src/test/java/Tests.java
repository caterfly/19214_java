import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class Tests {

    @Test
    public void SomeTests() {
        int[] testArr1 = {1,1,1,1};
        int[] sortedArr1 = {1,1,1,1};
        letsCheckIt(testArr1, sortedArr1);

        int[] testArr2 = {5,4,3,2,1};
        int[] sortedArr2 = {1,2,3,4,5};
        letsCheckIt(testArr2, sortedArr2);

        int[] testArr3 = {1234, -4321, 0, 22, 22, 33, 4435};
        int[] sortedArr3 = {-4321, 0, 22, 22, 33, 1234, 4435};
        letsCheckIt(testArr3, sortedArr3);

        int[] testArr4 = {3,4,5,6};
        int[] sortedArr4 = {3,4,5,6};
        letsCheckIt(testArr4, sortedArr4);
    }

    public void letsCheckIt(int[] arr, int[] sortedArr){
        HeapSort.main(arr);
        assertArrayEquals(sortedArr, arr);
    }
}