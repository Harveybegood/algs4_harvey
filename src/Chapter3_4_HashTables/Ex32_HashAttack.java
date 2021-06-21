package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashMap;
import java.util.Map;

/*
*   Hash attack. Find 2^N strings, each of length 2^N, that have the same hashCode() value, supposing that the hashCode()
*   implementation for String is the following:
*   Strong hint: Aa and BB have the same value
* */
@SuppressWarnings("unchecked")
public class Ex32_HashAttack {
    String s;
    String alternativeString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    int N = 2;
    int lengthOfString = (int) Math.pow(2, N);
    //int[] hashValueArray = new int[lengthOfString];
    Map<Integer, String>[] map = (Map<Integer, String>[]) new HashMap[lengthOfString];
    public int hashCode(){

        int hash = 0;
        for (int i = 0; i < length(); i++){
            hash = (hash * 31) + charAt(i);
        }
        return hash;
    }
    public int length(){
        return s.length();
    }
    public char charAt(int i){
        return s.charAt(i);
    }
    // generate string to which each of them has the same length of 2^N
    public String generateStringArray(){
        StringBuilder stringBuilder = new StringBuilder();
        String s1;
        for (int i = 0; i < lengthOfString; i++){
            s1 = String.valueOf(alternativeString.charAt(StdRandom.uniform(0, 51)));
            stringBuilder.append(s1);
        }
        return String.valueOf(stringBuilder);
    }
    // a method to find 2^N strings that have the same hashCode() value
    public void findStringWithSameHashCodeValue(Ex32_HashAttack hashAttack){
        //Ex32_HashAttack hashAttack = new Ex32_HashAttack();
        int numberOfStringWithSameHashCodeValue = 0;
        /*strings[0] = hashAttack.generateStringArray();
        s = strings[0];
        int hashValue = hashAttack.hashCode();
        map.put(hashValue, s);*/

        for (int i = 0; i < map.length; i++){
            s = hashAttack.generateStringArray();
            int hashValue = hashAttack.hashCode();
            //hashValueArray[i] =  hashValue;
            map[i].put(hashValue, s);

            int temp = i;
            while (temp > 0){
                if (numberOfStringWithSameHashCodeValue == lengthOfString){
                    break;
                }
                if (map[i] == map[temp - 1]){
                    numberOfStringWithSameHashCodeValue++;
                    StdOut.println(map[i].get(map[i]) + " " + map[i].get(map[temp - 1]) + " " + map[i]);
                }
                temp--;
            }
        }
    }

    public static void main(String[] args) {
        Ex32_HashAttack hashAttack = new Ex32_HashAttack();
        hashAttack.findStringWithSameHashCodeValue(hashAttack);

    }

    /*
        This solution is in https://algs4.cs.princeton.edu/34hash/
        It looks like I am confused with the question of what the exercise is asking

    * Solution. It is easy to verify that "Aa" and "BB" hash to the same hashCode() value (2112). Now, any string of
    * length 2N that is formed by concatenating these two strings together in any order (e.g., BBBBBB, AaAaAa, BBAaBB, AaBBBB)
    * will hash to the same value. Here is a list of 10000 strings with the same hash value.
    *
    * */
}
