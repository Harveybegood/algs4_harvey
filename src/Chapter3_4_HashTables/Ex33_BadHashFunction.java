package Chapter3_4_HashTables;
/*
*   Bad hash function. Consider the following hashCode() implementation for String, which was used in early versions of Java:
*
*   Explain why you think the designers chose this implementation and then why you think it was abandoned in favor of the one
*   in the previous exercise.
*
* */
public class Ex33_BadHashFunction {
    String s;
    // the one in the previous exercise
    public int hashCode(){
        int hash = 0;
        for (int i = 0; i < s.length(); i++){
            hash = (hash * 31) + s.charAt(i);
        }
        return hash;
    }

    // the one used in early versions of Java
    public int hashCode_Early(){
        int hash = 0;
        int skip = Math.max(1, s.length()/8);
        for (int i = 0; i < s.length(); i += skip){
            hash = (hash * 37) + s.charAt(i);
        }
        return hash;
    }

    /*
    *   Why chose a skip as Math.max(1, s.length()/8):
    *
    *
    *   Why it was abandoned:
    *
    *   The solution is in https://algs4.cs.princeton.edu/34hash/
    *
    *   This was done in the hopes of computing the hash function more quickly. Indeed, the hash values were computed more quickly,
    *   but it became more likely that many strings hashed to the same values. This resulted in a significant degradation
    *   in performance on many real-world inputs (e.g., long URLs) which all hashed to the same value,
    *   e.g., http://www.cs.princeton.edu/algs4/34hash/*****java.html.
    *
    * */
}
