package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.StdOut;

/*
*   Consider modular hashing for string keys with R = 256 and M = 255. Show that this is a bad choice because any permutation of
*   letters within a string hashes to the same value.
*
* */
public class Ex23_ShowBadChoiceWithR256M255 {
    // the hashCode() method for a string keys as below
    private String s1 = "string";
    private String s2 = "gnirts";
    public int hashCode1(){
        int R = 256;
        int M = 255;
        int hash = 0;
        for (int i = 0; i < s1.length(); i++){
            hash = (R * hash + s1.charAt(i)) % M;
            StdOut.println("hash value of each string: " + hash);
        }
        return hash;
    }
    public int hashCode2(){
        int R = 256;
        int M = 255;
        int hash = 0;
        for (int i = 0; i < s2.length(); i++){
            hash = (R * hash + s2.charAt(i)) % M;
            StdOut.println("hash value of each string: " + hash);
        }
        return hash;
    }
    public static void main(String[] args) {
        Ex23_ShowBadChoiceWithR256M255 showBadChoiceWithR256M255 = new Ex23_ShowBadChoiceWithR256M255();
        StdOut.println(showBadChoiceWithR256M255.hashCode1());
        StdOut.println(showBadChoiceWithR256M255.hashCode2());
        //StdOut.println("The value of hashcode: " + s.hashCode());
    }

    /*
    *   if R is a power of 2, there are coming up with same hash value. if R is a prime number, then not.
    *
    * */
}
