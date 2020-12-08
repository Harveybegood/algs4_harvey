package Chapter2_4_PriorityQueues;

/*
*       Describe a way to avoid the j < N Test in sink();
*
* */
public class Ex2_4_13<Key extends Comparable<Key>> {
    private int N = 0;
    private Key[] pq;
    public void sink(int k){
       while (2 * k < N){
           int j = 2 * k;
           if (pq[j].compareTo(pq[j + 1]) < 0)
               j++;
           if (!(pq[k].compareTo(pq[j]) < 0))
               break;
           Key temp = pq[j];
           pq[j] = pq[k];
           pq[k] = temp;
           k = j;
       }
    }
}
