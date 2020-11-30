package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

/*
*   Unbiased election. In order to thwart bias against candidates whose names appear toward the end of the alphabet,
*   California sorted the candidates appearing on its 2003 gubernatorial ballot by using the following order of characters:
*
*       R   W   Q   O   J   M   V   A   H   B   S   G   Z   X   N   T   C   I   E   K   U   P   D   Y   F   L
*
*   Create a data type where this is the natured order and write a client California with a single static method main()
*   that sorts strings according to this ordering. Assume that each string is composed solely of uppercase letters.
*
* */
public class Ex16_UnbiasedElection {
    public static class California{
        public static final Comparator<String> CANDIDATE_ORDER = new CandidateComparator();
        private static class CandidateComparator implements Comparator<String>{
            private static String order = "RWQOJMVAHBSGZXNTCIEKUPDYFL";
            public int compare(String a, String b){
                int n = Math.min(a.length(), b.length());
                for (int i = 0; i < n; i++) {
                    int aIndex = order.indexOf(a.charAt(i));
                    int bIndex = order.indexOf(b.charAt(i));
                    if (aIndex < bIndex)
                        return -1;
                    else if (aIndex > bIndex)
                        return 1;
                }
                return a.length() - b.length();
            }
        }
    }
    public static void main(String[] argv) {
        String[] candidates = StdIn.readAll().toUpperCase().split("\\n+");
       /* String strings = Constants.unbiasedElection + "california-gov.txt";
        String[] candidates =  strings.toUpperCase().split("\\n+");*/
        int n = candidates.length;
        Arrays.sort(candidates, California.CANDIDATE_ORDER);
        for (int i = 0; i < n; i++){
            StdOut.println(candidates[i]);
        }
    }
}
