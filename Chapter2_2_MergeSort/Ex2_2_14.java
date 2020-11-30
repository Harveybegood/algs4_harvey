package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   Merging sorted queues. Develop a static method that takes two queues of sorted items as arguments and returns a queue
*   that results from merging the queues into sorted order.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_2_14 {
    public static void main(String[] args) {
        String array1 = "A B C";
        String array2 = "D E F";
        Queue<String> q1 = generateQueue(array1);
        Queue<String> q2 = generateQueue(array2);
        StdOut.print(q1.toString() + " ");
        StdOut.println(" q1 string ");
        StdOut.print(q2.toString() + " ");
        StdOut.println(" q2 string ");
        Queue<String> newQueue = mergeSortedQueue(q1, q2);
        /*for (int i = 0; i < newQueue.size(); i++){
            StdOut.print(newQueue.dequeue() + " ");
        }*/
        for (String s : newQueue){
            StdOut.print(s + " ");
        }
    }


    private static Queue<String> generateQueue(String string){
        Queue<String> q = new Queue<>();
        String[] stringArray = string.split("\\s+");
        for (int i = 0; i < stringArray.length; i++){
            q.enqueue(stringArray[i]);
        }
        StdOut.print(q.toString() + " ");
        StdOut.println(" --- ");
        return q;
    }
    private static Queue<String> mergeSortedQueue(Queue<String> q1, Queue<String> q2){
        //Queue[] array = new Queue[1];
        String[] q1Array = new String[q1.size()];
        String[] q2Array = new String[q2.size()];
        int q1Index = 0;
        for (String value : q1){
            q1Array[q1Index++] = value;
        }
        int q2Index = 0;
        for (String value : q2){
            q2Array[q2Index++] = value;
        }
        //int N = q1.size() + q2.size();
        Queue<String> newQueue = new Queue<>();
        int leftIndex = 0, rightIndex = 0;
        while (leftIndex < q1Array.length && rightIndex < q2Array.length){
            if (q1Array[leftIndex].compareTo(q2Array[rightIndex]) < 0){
                newQueue.enqueue(q1Array[leftIndex++]);
            }else
                newQueue.enqueue(q2Array[rightIndex++]);
        }
        while (leftIndex < q1Array.length){
            newQueue.enqueue(q1Array[leftIndex++]);
        }
        while (rightIndex < q2Array.length){
            newQueue.enqueue(q2Array[rightIndex++]);
        }
        return newQueue;
    }
}
