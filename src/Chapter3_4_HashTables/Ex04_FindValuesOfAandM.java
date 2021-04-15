package Chapter3_4_HashTables;


import edu.princeton.cs.algs4.StdOut;

/*
*   Write a program to find values of a and M, with M as small as possible, such that the hash function (a * k) % M for
*   transforming the kth letter of the alphabet into a table index produces distinct values(no collisions)for the keys
*   S E A R C H X M P L. The result is known as a perfect hash function.
*
* */
public class Ex04_FindValuesOfAandM {
    // To initialize an array to store all previous table index
    private int[] tableIndex = new int[10];
    public Ex04_FindValuesOfAandM(){
        for (int i = 0; i < 10; i++){
            tableIndex[i] = -1;
        }
    }

    // Firstly, we assume M is the number of keys.
    private int hash(String[] keys, int n){
        // on account of the property of modulo and the length of keys. We can assume the M is 10(the length of keys)
        int M = keys.length;
        // the kth letter of keys(S E A R C H X M P L) that we also assume each of them lies at the index of an array of table,
        // accordingly, which ranges from 0 to the length of keys minus 1;
        int k = 0;
        int index = 0;
        int a = 1;
        int hashValue = (a * k) % M;
        tableIndex[index] = hashValue;
        index++;
        k++;
        for ( ; a < n; a++){
            while (index == 1 || isDistinctValues(tableIndex, hashValue, index)){
                hashValue = (a * k) % M;
                tableIndex[index] = hashValue;
                index++;
                k++;
                if (index == 10){
                    break;
                }
            }
            if (index == 10){
                break;
            }
        }
        return a;
    }
    public boolean isDistinctValues(int[] tableIndex, int hashValue, int index){
        for (int i = index - 2; i >= 0; i--){
            if (hashValue == tableIndex[i]){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Ex04_FindValuesOfAandM findValuesOfAandM = new Ex04_FindValuesOfAandM();
        String[] keys = {"S", "E", "A", "R", "C", "H", "X", "M", "P", "L"};
        int n = 10;
        StdOut.println(findValuesOfAandM.hash(keys, n));
    }
}
