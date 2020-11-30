package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex2_3_20_1 {
    private static class Stack{

        private static Comparable[] items = new Comparable[1];
        private static int size;
        public void resize(int newSize){
            Comparable[] newItems = new Comparable[newSize];
            for (int i = 0; i < size; i++){
                newItems[i] = items[i];
            }
            items = newItems;
        }
        public boolean isEmpty(){return size == 0;}
        public Stack push(Comparable item){
            if (size == items.length){
                resize(size * 2);
            }
            items[size++] = item;
            return this;
        }
        public Comparable pop(){
            if (size == 0) throw new RuntimeException("empty Chapter1_3_Bags_Queues_Stacks.stack");
            Comparable item = items[--size];
            if (size > 0 && size == items.length / 4){
                resize(items.length / 2);
            }
            return item;
        }
    }
    public static void nonRecursiveQuickSort(Comparable[] array){
        Stack stack = new Stack();
        stack.push(0).push(array.length - 1);

        nonRecursiveQuickSort(array, stack);
    }
    @SuppressWarnings("unchecked")
    public static void nonRecursiveQuickSort(Comparable[] array, Stack stack){
        while (!stack.isEmpty()){
            int high = (int) stack.pop(), low = (int)stack.pop();
            int i = low, j = high + 1;
            Comparable pivot = array[low];
            while (true){
                while (i < high && array[++i].compareTo(pivot) < 0);
                while (j > low && array[--j].compareTo(pivot) > 0);
                if (i >= j) break;
                Comparable temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
            Comparable temp = array[low];
            array[low] = array[j];
            array[j] = temp;
            if (j + 1 < high)
                stack.push(j+1).push(high);
            if (low < j - 1)
                stack.push(low).push(j-1);
        }
    }
    private static Comparable[] generateArray(int n){
        Comparable[] array = new Comparable[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(1, 100);
        }
        return array;
    }
    public static void main(String[] args) {
        Comparable[] array = generateArray(10);
        nonRecursiveQuickSort(array);
        for (Comparable x : array){
            StdOut.print(x + " ");
        }
    }
}
