package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;

/*
*   Linked-list sort. Implement a natural mergesort for linked lists. (This is the method of choice for sorting linked lists
*   because it uses no extra space and is guaranteed to be linearithmic.)
*
* */
public class Ex2_2_17 {
    public static Integer[] generateArray(int n){
        Integer[] newArray = new Integer[n];
        for (int i =0; i < n; i++){
            newArray[i] = StdRandom.uniform(1, 100);
        }
        return newArray;
    }
    public static LinkedList linkedList(){
        Integer[] array = generateArray(20);
        //LinkedList<Integer> list =new ArrayList<Integer>();
        LinkedList<Integer> list = new LinkedList<>();
        for (Integer n : array){
            list.add(n);
        }

        return list;
    }
   /* public static void linkedListSort(LinkedList list){
        if (list == null || list.size() == 1)return;

    }*/

    public static void main(String[] args) {
        LinkedList list = linkedList();
        for (int i = 0; i < 20; i++){
            StdOut.print(list.pop() + " ");
        }
        StdOut.println();
    }
}
