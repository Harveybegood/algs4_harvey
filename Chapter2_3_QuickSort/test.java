package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;


public class test {
    private  static void quickSort_3(int[] array, int low, int high) {
        if(low >= high)
            return;
        int pivot = array[low], temp;
        int left = low + 1, lFlag = low + 1, right = high, rFlag = high;
        while(true) {
            while(array[left] <= pivot && left <= high) {
                if(array[left] == pivot) {
                    temp=array[left];
                    array[left] = array[lFlag];
                    array[lFlag] = temp;
                    lFlag++;
                }
                left++;
            }
            while(array[right] >= pivot && right >= low) {
                if(array[right] == pivot) {
                    temp=array[right];
                    array[right] = array[rFlag];
                    array[rFlag] = temp;
                    rFlag--;
                }
                right--;
            }
            if(left >= right)
                break;
            temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
        lFlag--;left--;
        while(lFlag >= low) {
            temp = array[left];
            array[left] = array[lFlag];
            array[lFlag] = temp;
            left--;
            lFlag--;
        }
        rFlag++;
        right++;
        while(rFlag <= high) {
            temp = array[right];
            array[right] = array[rFlag];
            array[rFlag] = temp;
            right++;
            rFlag++;
        }
        quickSort_3(array, low, left);
        quickSort_3(array, right, high);
    }
    public static void main(String[] args) {
        int array[] = {2,5,9,8,2,2,7,8,1,6,3};
        StdOut.println(array.length);
        quickSort_3(array,0,array.length - 1);
        for(int i = 0; i < array.length; i++)
            StdOut.printf("%d  ",array[i]);
        StdOut.println();
        StdOut.println(array.length);
    }
}
