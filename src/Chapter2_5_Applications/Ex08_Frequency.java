package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Write a program Frequency that reads strings from standard input and prints the number of times each string occurs,
*   in descending order of frequency.
*
*   Idea: firstly, i shall do sorting to the strings input from standard input.
*         since, the string is already sored in descending order, then we take each of character from index of 0
*         until the last index. during the operation, each of item will be compared with its adjourning next item,
*         if it is equal, the defined variable like count will plus one time.
*
* */
public class Ex08_Frequency {
    private static int cnt;
    public static void main(String[] args) {
        String s = StdIn.readString();
        frequency(s);
    }
    public static String[] insertion(String s){
        int n = s.length();
        String[] array = new String[n];
        for (int i = 0; i < n; i++){
            array[i] = s.substring(i, i + 1);
            StdOut.print(array[i] + " ");
        }
        for (int i = 1; i < array.length; i++){
            for (int j = i; j > 0 && array[j].compareTo(array[j - 1]) < 0; j--){
                String temp = array[j]; array[j] = array[j - 1]; array[j - 1] = temp;
            }
        }
        return array;
    }
    public static void frequency(String s){
        String[] array = insertion(s);
        for (int i = 0; i < array.length; i++){
            cnt = 1;
            for (int j = i + 1; j < array.length; j++){
                if (array[i].equals(array[j])){
                    cnt++;
                    i++;
                }
            }
            StdOut.println(array[i] + " " + cnt);
        }
    }
}
