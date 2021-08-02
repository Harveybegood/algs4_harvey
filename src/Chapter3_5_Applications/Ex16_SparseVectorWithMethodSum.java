package Chapter3_5_Applications;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdOut;


/*
*   Add a method sum() to SparseVector that takes a SparseVector as argument and returns a SparseVector that is the term-by-term sum of this
*   vector and the argument vector. Note: You need to delete()(and special attention to precision)to handle the case where an entry becomes 0.
*
* */
public class Ex16_SparseVectorWithMethodSum {
    private LinearProbingHashST<Integer, Double> hashST;
    public Ex16_SparseVectorWithMethodSum(){
        hashST = new LinearProbingHashST<>();
    }
    public int size(){return hashST.size();}
    public void put(int i, double x){
        hashST.put(i, x);
    }
    public double get(int i){
        if (!hashST.contains(i)){return 0.0;}
        else {return hashST.get(i);}
    }
    public double dot(double[] that){
        double sum = 0.0;
        for (int i : hashST.keys()){
            sum += that[i] * this.get(i);
        }
        return sum;
    }
    public void delete(int i){
        hashST.delete(i);
    }
    public Ex16_SparseVectorWithMethodSum sum(Ex16_SparseVectorWithMethodSum that){
        int lengthOfSymbolTable = Math.max(this.size(), that.size());
        for (int i = 0; i < lengthOfSymbolTable; i++){
            double sum = get(i) + that.get(i);
            if (sum != 0){
                put(i, sum);
            }
            else {
                delete(i);
            }
        }
        return this;
    }

    public static void main(String[] args) {
        Ex16_SparseVectorWithMethodSum firstSparseVector = new Ex16_SparseVectorWithMethodSum();
        firstSparseVector.put(0, .90);
        firstSparseVector.put(1, .36);
        firstSparseVector.put(2, .90);
        firstSparseVector.put(3, .0);
        firstSparseVector.put(4, .90);
        firstSparseVector.put(5, .47);

        Ex16_SparseVectorWithMethodSum secondSparseVector = new Ex16_SparseVectorWithMethodSum();
        secondSparseVector.put(0, .05);
        secondSparseVector.put(1, .04);
        secondSparseVector.put(2, .0);
        secondSparseVector.put(3, .0);
        secondSparseVector.put(4, .37);
        secondSparseVector.put(5, .19);

        Ex16_SparseVectorWithMethodSum sumOfTwoSparseVector = firstSparseVector.sum(secondSparseVector);
        int lengthOfSymbolTable = Math.max(firstSparseVector.size(), secondSparseVector.size());
        for (int i = 0; i < lengthOfSymbolTable; i++){
            StdOut.printf("%.2f", sumOfTwoSparseVector.get(i));
            StdOut.print(" ");
        }
    }
}

/*
*           0.95 0.40 0.90 0.00 1.27 0.66
 *
* */