package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.sql.Struct;
import java.util.Arrays;
import java.util.IllegalFormatCodePointException;

/*
*   Hot or cold. Your goal is to guess a secret integer between 1 and N. You repeatedly guess integers between 1 and N. After each guess
*   you learn if your guess equals the secret integer(and the game stops). Otherwise, you learn if the guess is hotter(closer to) or colder
*   (farther from) the secret number than your previous guess. Design an algorithm that finds the secret number in at most ~2lgN guesses. Then
*   design an algorithm that finds the secret number in at most ~1lgN guesses.
*
* */
public class Ex34_HotOrCold<Item> {

    // finds the secret number in at most ~2lgN guesses
    public int guessSecretIntegerWith2lgN(int[] array, int secretIntegerIndex){
        Arrays.sort(array);
        // initially, assume the first number equals the secret number
        if (array[0] == array[secretIntegerIndex]){
            StdOut.println("The secret number is " + array[0]);
            return array[0];
        }
        // if the secret number does not equal the first number which then will be considered as previous number, and then we assume
        // the last number that is current guess number, and then we have both previous number and current number compared with secret number
        // anyhow, hotter number will be the next previous number and the middle number of both will be the current number
        return guessSecretIntegerWith2lgN(0, array.length - 1, array, secretIntegerIndex);
    }
    public int guessSecretIntegerWith2lgN(int previousIndex, int currentIndex, int[] array, int secretIntegerIndex){
        //
        boolean hotter = currentGuessIsHotter(previousIndex, currentIndex, secretIntegerIndex);
        int middleIndex = previousIndex + (currentIndex - previousIndex) / 2;
        if (array[middleIndex] == array[secretIntegerIndex]){
            return array[secretIntegerIndex];
        }
        // if current guess is hotter the secret number than the previous, replace the previous number to guess number which is an actual middle number
        if (hotter){
            return guessSecretIntegerWith2lgN(middleIndex + 1, currentIndex, array, secretIntegerIndex);
        }
        else {
            return guessSecretIntegerWith2lgN(previousIndex, middleIndex - 1, array, secretIntegerIndex);
        }
        //return -1;
    }
    public boolean currentGuessIsHotter(int previousIndex, int currentIndex, int secretNumberIndex){
        int lowBound = secretNumberIndex - previousIndex;
        int highBound = currentIndex - secretNumberIndex;
        if (lowBound > highBound){
            return true;
        }
        else {
            return false;
        }
    }

    // finds the secret number in at most ~1lgN guesses
    public int guessSecretIntegerWith1lgN(int[] array, int secretIntegerIndex){
        Arrays.sort(array);
        if (array[0] == array[secretIntegerIndex]){
            return array[secretIntegerIndex];
        }
        // array[0] is considered as a previous guess
        return guessSecretIntegerWith1lgN(0, array.length - 1, array, secretIntegerIndex);
    }
    public int guessSecretIntegerWith1lgN(int previousIndex, int currentIndex, int[] array, int secretIntegerIndex){
        int middleIndex = previousIndex + (currentIndex - previousIndex) / 2;
        // Assume that the current guess is hotter
        if (array[middleIndex] < array[secretIntegerIndex]){
            return guessSecretIntegerWith1lgN(middleIndex, currentIndex, array, secretIntegerIndex);
        }
        // Assume that the previous guess is hotter
        else if (array[middleIndex] > array[secretIntegerIndex]){
            return guessSecretIntegerWith1lgN(previousIndex, middleIndex, array, secretIntegerIndex);
        }
        else {
            return array[middleIndex];
        }
        //return -1;
    }
    // write a method to determine both of previous guess and current guess which is hotter
    /*public boolean currentGuessIsHotter(int previousIndex, int currentIndex, int[] array, int secretNumber){
        if ((array[currentIndex] - secretNumber) > (secretNumber - array[previousIndex])){
            return true;
        }
        return false;
    }*/
    public int[] generateArray(int n){
        int[] array = new int[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(-20, 20);
        }
        return array;
    }
    public static void main(String[] args) {
        Ex34_HotOrCold<Integer> hotOrCold = new Ex34_HotOrCold<>();
        int[] array = hotOrCold.generateArray(20);
        int secretNumberIndex = 10;
        int guessNumber = hotOrCold.guessSecretIntegerWith2lgN(array, secretNumberIndex);
        StdOut.println(guessNumber);
        int[] array1 = hotOrCold.generateArray(20);
        int secretNumberIndex1 = 10;
        int guessNumber1 = hotOrCold.guessSecretIntegerWith1lgN(array1, secretNumberIndex1);
        StdOut.println(guessNumber1);
    }
}
