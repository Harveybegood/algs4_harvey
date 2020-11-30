package Chapter1_3_Bags_Queues_Stacks.stack;

import edu.princeton.cs.algs4.StdOut;

import java.util.StringJoiner;

public class test{
    int[] a = new int[5];
    public int N = 0;
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public void add(int number){
        if (a.length >= 5){resize(a.length*2);}
        a[N] = number;
        N++;
    }
    public int delete(){
        if (isEmpty()){throw new RuntimeException("Array underflow");}
        //int[] b = new int[];
        //b = Integer.parseInt(a[N]);
        int b = a[N];
        N--;
        if (N > 0 && N < a.length/2){resize(a.length/4);}
        return b;
    }
    public void resize(int newSize){
        int[] newArray = new int[newSize];
        for (int i = 0; i < a.length; i++){
            newArray[i] = a[i];
        }
        a = newArray;
    }

    public static void main(String[] args) {
        test newObject = new test();
        for (int i = 0; i < 8; i++){
            newObject.add(i);
        }
        StringJoiner splitNewObject = new StringJoiner(" -> ");
        for (int i = 0; i < newObject.size(); i++){
            splitNewObject.add(String.valueOf(newObject));
        }

        StdOut.println(splitNewObject);
    }
}