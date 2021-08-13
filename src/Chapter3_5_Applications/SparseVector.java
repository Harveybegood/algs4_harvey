package Chapter3_5_Applications;

import Chapter3_4_HashTables.LinearProbingHashST;

// This SparseVector implementation with dot product is for exercise Ex34_SparseVector test
public class SparseVector {
    private LinearProbingHashST<Integer, Double> st;
    public SparseVector(){
        st = new LinearProbingHashST<>();
    }
    //public int size(){return st.size();}
    public void put(int i, double x){
        st.put(i, x);
    }
    public double get(int i){
        if (!st.contains(i)){return 0.0;}
        return st.get(i);
    }
    public double dot(double[] that){
        double sum = 0.0;
        for (int i : st.keys()){
           /* if (st.get(i) == 0.0){
                continue;
            }*/
            sum += that[i] * st.get(i);
        }
        return sum;
    }
}
