package Chapter1_4_AnalysisOfAlgorithms;
/*
*   Amortized analysis. Prove that, starting from an empty stack, the number of array accesses used by any sequence of M
*   operations in the resizing array implementation of Stack is proportional to M.
* */
public class Ex32_AmortizedAnalysis {
    // an initial array, suppose its size of 2
    int[] array = new int[2];

    // a method to resize array
    public int[] resizingArray(int newSize){
        int[] newArray = new int[newSize];
        for (int i = 0; i < array.length; i++){
            newArray[i] = array[i];
        }
        return newArray;
    }
}
