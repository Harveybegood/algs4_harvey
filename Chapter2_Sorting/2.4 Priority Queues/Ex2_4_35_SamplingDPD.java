package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdRandom;
/*
*   Sampling from a discrete probability distribution. Write a class Sample with a constructor that takes an array p[] of
 *  double values as argument and supports the following two operations:
 *  random()- return an index i with probability p[i]/T (where T is the sum of the numbers in p[])
 *  change(i, v) - change the value of p[i] to v.
*   Hint:
*   Use a complete binary tree where each node has implied weight p[i].
*   Store in each node the cumulative weight of all the nodes in its subtree.
*   To generate a random index, pick a random number between 0 and T and use the cumulative weights to determine
*   which branch of the subtree to explore.
*   When updating p[i], change all of the weight of the nodes on the path from the root to i.
*   Avoid explicit pointers, as we do for heaps.
*
*   https://en.wikipedia.org/wiki/Binary_tree
 *   In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level
 *   are as far left as possible. It can have between 1 and 2^n nodes at the last level n.
* */
@SuppressWarnings("unchecked")
public class Ex2_4_35_SamplingDPD {
    private double[] pq;
    private double[] WeightSum;
    private double T;
    public Ex2_4_35_SamplingDPD(double[] p){
        // copy the weight from argument p[] to pq[], which means each node has assigned with a weight.
        pq = new double[p.length + 1];
        for (int i = 1; i < p.length; i++){
            pq[i] = p[i - 1];
            T += p[i - 1];
        }
        // store cumulative weight for each node that contains all weight of its subtree
        WeightSum = new double[p.length + 1];
        for (int i = p.length; i / 2 > 0; i--){
            WeightSum[i / 2] += p[i];
        }
    }

    // operation random(), return an index i with its probability p[i] / T, what is the "index i  with probability p[i]/T"?

    // operation change(i, v), change all of the weight of the nodes
    private void change(int i, double v){}

    public static void main(String[] args) {

    }
}
