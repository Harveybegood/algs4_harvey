package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

/*
*   Shellsort increments. Run experiments to compare the increment sequence in Algorithm2.3 with the sequence
*   1,5,19,41,109,209,505,929,2161,3905,8929,16001,36289,64769,146305,260609(which is formed by merging together
*   the sequence 9.4^k - 9.2^k+1 and 4^k - 3.2^k + 1). See Exercise 2.1.11
*
* */
public class Ex2_1_29 {
    public static void shellSortSequence1(Comparable[] array){
        int N = array.length;
        int h = 1;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(h);
        while (h < N / 3){
            for (int k = 0; k < 17; k++){
                int t1 = 9*4^k - 9*2^k + 1;
                int t2 = 4^k - 3*2^k + 1;
                if (k == 0){list.add(t1);}
                else if(k == 1){break;}
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void shellSortSequence2(Comparable[] array){
        int N = array.length;
        StdOut.print(N);
        StdOut.println();
        int h = 1;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(h);
        while (h < N / 3){
            list.add(h = h * 3 + 1);
        }
        int index = list.size();
        int[] hSort = new int[index];
        for (int i = 0; i < index; i++){
            hSort[i] = list.get(i);
        }
        int cursor = hSort.length - 1;
        StdOut.println(hSort[cursor]);
        while (cursor >= 0){
            for (int i = hSort[cursor]; i < N; i++){
                for (int j = i; j >= hSort[cursor] && array[j].compareTo(array[j - hSort[cursor]]) < 0; j -= hSort[cursor]){
                    Comparable temp = array[j];
                    array[j] = array[j-hSort[cursor]];
                    array[j-hSort[cursor]] = temp;
                }
            }
            cursor--;
        }
    }
    public static void show(Comparable[] array){
        StringBuilder stringBuilder = new StringBuilder(" ");
        for (Comparable s : array){
            StdOut.print(stringBuilder.append(s));
        }
    }
    public static void main(String[] args) {
        String[] strings = {"S", "O", "R", "T", "S", "H", "E", "L", "S", "O", "R", "T", "S", "H", "E",
                "L", "S", "O", "R", "T", "S", "H", "E", "L", "A"};
        shellSortSequence2(strings);
        //StdOut.print(strings + " ");
        show(strings);
    }
}
