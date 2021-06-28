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
    public int guessSecretIntegerWith2lgN(int[] array, int secretInteger){
        Arrays.sort(array);
        // initially, assume the first number equals the secret number
        if (array[0] == secretInteger){
            StdOut.println("The secret number is " + array[0]);
            return array[0];
        }
        // if the secret number does not equal the first number which then will be considered as previous number, and then we assume
        // the last number that is current guess number, and then we have both previous number and current number compared with secret number
        // anyhow, hotter number will be the next previous number and the middle number of both will be the current number
        return guessSecretIntegerWith2lgN(0, array.length - 1, array, secretInteger);
    }
    public int guessSecretIntegerWith2lgN(int previousIndex, int currentIndex, int[] array, int secretInteger){
        //
        int guessIndex = previousIndex + (currentIndex - previousIndex) / 2;
        if (array[guessIndex] == secretInteger){
            return array[guessIndex];
        }
        // if current guess is hotter the secret number than the previous, replace the previous number to guess number which is an actual middle number
        if (highEndIsHotter(previousIndex, currentIndex, array, secretInteger)){
            return guessSecretIntegerWith2lgN(guessIndex, currentIndex, array, secretInteger);
        }
        else {
            return guessSecretIntegerWith2lgN(previousIndex, guessIndex, array, secretInteger);
        }
        //return -1;
    }
    public boolean highEndIsHotter(int previousIndex, int currentIndex, int[] array, int secretNumber){
        int lowBound = Math.abs(secretNumber - array[previousIndex]);
        int highBound = Math.abs(array[currentIndex] - secretNumber);
        if (lowBound > highBound){
            return true;
        }
        else {
            return false;
        }
    }

    // finds the secret number in at most ~1lgN guesses
    public int guessSecretIntegerWith1lgN(int[] array, int secretInteger){
        Arrays.sort(array);
        return guessSecretIntegerWith1lgN(0, array.length - 1, array, secretInteger);
    }
    public int guessSecretIntegerWith1lgN(int low, int high, int[] array, int secretInteger){
        int guessNumber = low + (high - low) / 2;
        if (array[guessNumber] < secretInteger){
            return guessSecretIntegerWith1lgN(guessNumber + 1, high, array, secretInteger);
        }
        else if (array[guessNumber] > secretInteger){
            return guessSecretIntegerWith1lgN(low, guessNumber - 1, array, secretInteger);
        }
        else {
            return guessNumber;
        }
        //return -1;
    }
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
        int secretNumber = array[10];
        int guessNumber = hotOrCold.guessSecretIntegerWith2lgN(array, secretNumber);
        StdOut.println(guessNumber);
        int secretNumber1 = array[10];
        int guessNumber1 = hotOrCold.guessSecretIntegerWith1lgN(array, secretNumber1);
        StdOut.println(guessNumber1);
    }
}
