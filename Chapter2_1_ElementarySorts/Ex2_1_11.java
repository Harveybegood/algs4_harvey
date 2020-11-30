package Chapter2_1_ElementarySorts;

import java.util.ArrayList;

/*
*   Implement a version of shellsort that keeps the increment sequence in an array, rather than computing it.
*
* */
public class Ex2_1_11 {
    public static void shellSOrt(Comparable[] a){
        int N = a.length;
        int h = 1;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(h);
        while (h <= N/3){
            list.add(h = h * 3 + 1);
        }
        //int[] hsort = list.toArray();
        //StringBuilder stringBuilder = new StringBuilder(" ");
        int[] hsort = new int[N];
        for (int i = 0; i < list.toArray().length; i++){
            hsort[i] = list.toString().indexOf(i);
        }
       /* for (int i : list){
            hsort[i] = list.toString().indexOf(i);
        }*/

        int index = hsort.length - 1;
        while (hsort[index] >= 1){
            for (int i = hsort[index]; i < N; i++){
                for (int j = i; j >= hsort[index] && less_exch.less(a[j], a[j-1]); j -= hsort[index]){
                    less_exch.exch(a, j, j-1);
                }
            }
            index--;
        }
    }

    public static void main(String[] args) {
        String[] a = {"E", "A", "S", "Y", "S", "H", "E", "L", "L", "S", "O", "R", "T", "Q", "U", "E", "S", "T", "I",
                "O", "N"};
        shellSOrt(a);
        less_exch.show(a);
    }

}
