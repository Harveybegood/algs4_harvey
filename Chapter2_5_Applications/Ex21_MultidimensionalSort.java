package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdOut;

/*
*   Multidimensional sort. Write a Vector data type for use in having the sorting methods sort multidimensional vectors
*   of d integers, putting the vectors in order by first component, those with equal first component in order by second
*   component, those with equal first and second components in order by third component, and so forth.
*
* */
public class Ex21_MultidimensionalSort{
    private class VectorDataType{
        private int first;
        private int second;
        private int third;
        public VectorDataType(int first, int second, int third){
            this.first = first;
            this.second = second;
            this.third = third;
        }
        public int getFirst(){
            return first;
        }
        public int getSecond(){
            return second;
        }
        public int getThird(){
            return third;
        }
        public String toString(){
            return first + "," + second + "," + third;
        }
        public int compareTo(VectorDataType that){
            if (this.first < that.first)
                return -1;
            else if (this.first > that.first)
                return 1;
            else {
                if (this.second < that.second){
                    return -1;
                }
                else if (this.second > that.second){
                    return 1;
                }
                else {
                    if (this.third < that.third){
                        return -1;
                    }
                    else if (this.third > that.third){
                        return 1;
                    }
                    else {
                        return 0;
                    }
                }
            }
        }
    }
    public void sortDimensionalVectors(VectorDataType vector1, VectorDataType vector2){
        if (vector1.compareTo(vector2) > 0){
            StdOut.println(vector1);
        }
        else {
            StdOut.println(vector2);
        }
    }

    public static void main(String[] args) {
        Ex21_MultidimensionalSort multidimensionalSort = new Ex21_MultidimensionalSort();
        VectorDataType vector1 = multidimensionalSort.new VectorDataType(10, 1, 20);
        VectorDataType vector2 = multidimensionalSort.new VectorDataType(10, 11, 20);
        StdOut.print("Expected value 10,11,20 -> ");
        multidimensionalSort.sortDimensionalVectors(vector1, vector2);
        VectorDataType vector3 = multidimensionalSort.new VectorDataType(10, 11, 30);
        VectorDataType vector4 = multidimensionalSort.new VectorDataType(11, 1, 20);
        VectorDataType vector5 = multidimensionalSort.new VectorDataType(12, 1, 20);
        StdOut.println();
        StdOut.print("Expected value 11,1,20 -> ");
        multidimensionalSort.sortDimensionalVectors(vector3, vector4);
        StdOut.println();
        StdOut.print("Expected value 12,1,20 -> ");
        multidimensionalSort.sortDimensionalVectors(vector3, vector5);
    }
}
