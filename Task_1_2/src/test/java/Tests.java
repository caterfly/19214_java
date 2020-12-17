import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertArrayEquals;


public class Tests {

    @Test
    public void SomeTests() {
        String fileName1 = "input1.txt";
        String subStr1 = "aaa";
        Long[] testedArr1 = new Long[]{0L, 1L, 2L, 3L, 4L};
        letsCheckIt(fileName1, subStr1, testedArr1);

        String fileName2 = "input2.txt";
        String subStr2 = "пирог";
        Long[] testedArr2 = new Long[] {7L};
        letsCheckIt(fileName2, subStr2, testedArr2);

        String  fileName3 = "input3.txt";
        String subStr3 = "пирог";
        Long[] testedArr3 = new Long[]{};
        letsCheckIt(fileName3, subStr3, testedArr3);

        String fileName4 = "input4.txt";
        String subStr4 = "ᤆᤆ";
        Long[] testedArr4 = {0L, 1L, 2L};
        letsCheckIt(fileName4, subStr4, testedArr4);



    }

    public void letsCheckIt(String fileName, String subStr, Long[] testedArr) {
        try (InputStream in = new FileInputStream(fileName)) {
            assertArrayEquals(testedArr, (SearchSubstring.searchSubStrings(in, subStr)).toArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}