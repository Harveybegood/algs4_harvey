package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdOut;

/*
*   Kendall tau distance. Write a program kendallTau.java that computes the Kendall tau distance between two
*   permutations in linearithmic time.
*
* */
public class Ex19_KendallTauDistance {
    public static void main(String[] args) {
        Ex19_KendallTauDistance kendallTauDistance = new Ex19_KendallTauDistance();
        int[] a1 = {0, 3, 1, 6, 2, 5, 4};
        int[] a2 = {1, 0, 3, 6, 4, 2, 5};

       /* for (int i = 0; i < a1.length; i++){
            a2[i] = a1[i + StdRandom.uniform(a1.length - i)];
        }*/
        for (int i = 0 ; i < a1.length; i++){
            StdOut.print(a1[i] + " ");
        }
        StdOut.println();
        for (int i = 0; i < a2.length; i++){
            StdOut.print(a2[i] + " ");
        }
        StdOut.println();
        StdOut.println(kendallTauDistance.KTDistance(a1, a2));
    }

  /*  public int[] generatePermutation(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i + StdRandom.uniform(20 - i);
        }
        return array;
    }*/
    public int KTDistance(int[] a, int[] b){
        int count = 0;
      /*  int j = 0;
        int i = 1;*/
       /* while (j < b.length){
            for (; i < a.length; i++){
                if ((a[i] - a[j] * b[i] - b[j]) < 0){
                    count++;
                }
            }
            ++j;
            i = j + 1;
        }*/
       for (int i = 0, j = 1; j < a.length; i++, j++){
           if ((a[i] - a[j]) * (b[i] - b[j]) < 0){
               count++;
           }
        }
        return count;
    }
}
