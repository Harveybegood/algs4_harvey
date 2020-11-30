package Chapter2_2_MergeSort;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Sublinear extra space. Develop a merge implementation that reduces the extra space requirement to max(M, N/M),
*   based on the following idea: Divide the array into N/M blocks of size M(for simplicity in this description,
*   assume that N is a multiple of M). Then,(i) considering the blocks as items with their first key as the sort key,
*   sort them using selection sort; and (ii) run through the array merging the first block with the second, then the
*   second block with the third, and so forth.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_2_12 {
    private static void mergeBlock(Comparable[] array, int M){
        int N = array.length;
        int blockSize = N / M;
        Comparable[] aux = new Comparable[blockSize];
        int low = 0;
        int middle = blockSize - 1;
        int high = middle + blockSize;
        int blockMerged = 1;
        while (high < N){
            merge(array, aux, low, middle, high);
            blockMerged++;
            low = (blockMerged - 1) * blockSize;
            middle = blockMerged * blockSize - 1;
            high = middle + blockSize;
        }
    }
    private static void merge(Comparable[] array, Comparable[] aux, int low, int middle, int high){
        int auxIndex = 0;
        for (int i = low; i <= middle; i++){
            aux[auxIndex] = array[i];
            auxIndex++;
        }
        int leftIndex = 0;
        int rightIndex = middle + 1;
        int arrayIndex = low;
        while (leftIndex < aux.length && rightIndex <= high){
            if (aux[leftIndex].compareTo(aux[rightIndex]) < 0){
                array[arrayIndex] = aux[leftIndex];
                leftIndex++;
            }else {
                array[arrayIndex] = aux[rightIndex];
                rightIndex++;
            }
            arrayIndex++;
        }
        while (leftIndex < aux.length){
            array[arrayIndex] = aux[leftIndex];
            leftIndex++;
        }
    }
    private static void selectionSortBlock(Comparable[]array, int M){
        for (int i = 0; i < array.length; i += array.length / M){
            selectionSort(array, i, i + array.length / M - 1);
        }
    }
    private static void selectionSort(Comparable[] array, int low, int high){
        for (int i = low; i < high; i++){
            int min = i;
            for (int j = i + 1; j < high; j++){
                if (array[j].compareTo(min) < 0){
                    min = j;
                }
            }
            Comparable temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }
    private static Comparable[] generateArray(int n){
        Comparable[] arrayElements = new Comparable[n];
        for (int i = 0; i < arrayElements.length; i++){
            arrayElements[i] = StdRandom.uniform(1, 100);
        }
        return arrayElements;
    }

    public static void main(String[] args) {
        Comparable[] newArray = generateArray(50);
        selectionSortBlock(newArray, 10);
        for (int i = 0; i < newArray.length / 10; i++){
            mergeBlock(newArray, 10);
        }
        for (int i = 0; i < newArray.length; i++){
            StdOut.println(newArray[i]);
        }
    }
}
