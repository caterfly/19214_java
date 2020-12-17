import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SearchSubstring {


    /**
     * @param inStream - Input stream where we find substrings
     * @param subString - string that we want to find in inStream
     * @return  array of the indexes which mean that subString begins from that index
     */
    public static ArrayList<Long> searchSubStrings(InputStream inStream, String subString) throws IOException {

        ArrayList<Long> indexes = new ArrayList<>();
        String subStringName = new String(subString.getBytes(),StandardCharsets.UTF_8);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, StandardCharsets.UTF_8))) { // one more battle
            int ch;                     //
            long headSubstring = 0;      // write here position in file of substring's beginning
            long currPosInFile = 0;      // we go through the file char by char
            int currPosInSubstring = 0; //
            boolean isSubString = false;

            while ((ch = reader.read()) != -1) {

                if (currPosInSubstring == subStringName.length() ||                          //if we get to subString's end
                        ((char) ch != subStringName.charAt(currPosInSubstring) && isSubString)) {

                    if (currPosInSubstring == subStringName.length()) indexes.add(headSubstring); // add to arr
                    isSubString = false;                // indicate that
                    currPosInSubstring = 0;
                    reader.reset();
                    currPosInFile = headSubstring + 1;
                    continue;
                }

                if ((char) ch == subStringName.charAt(currPosInSubstring)) {  //if char from file equals to char from substring
                    if (!isSubString) {                  //if we meet beginning of substring then remember subString's head
                        headSubstring = currPosInFile;
                        isSubString = true;
                        reader.mark(subStringName.length());
                    }
                    currPosInSubstring++;
                }

                currPosInFile++;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexes;
    }
}