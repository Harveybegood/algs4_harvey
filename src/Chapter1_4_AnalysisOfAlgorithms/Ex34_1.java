package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

public class Ex34_1 {

    private int hotOrCold(int number, int target){return firstGuesses(number,target,1,number);}
    private int firstGuesses(int number, int target, int low, int high){
        // check if it is in the first half
        int firstGuessIndex = number / 2;
        if (firstGuessIndex == target){
            StdOut.println("Found it!");
            return firstGuessIndex;
        }
        // check it it is in the second half
        int secondGuessIndex = (number/2) + 1;
        if (secondGuessIndex == target){
            StdOut.println("Found it!");
            return secondGuessIndex;
        }else {
            boolean isItHotter = isItHotter(firstGuessIndex, secondGuessIndex, target);
            if (isItHotter){
                return binarySearch(target,secondGuessIndex,secondGuessIndex,high);
            }else {
                return binarySearch(target, secondGuessIndex, low, firstGuessIndex);
            }
        }
    }
    private int binarySearch(int target, int lastGuess, int low, int high){
        if (low == high){
            if (low == target){
                return low;
            }else return -1;
        }
        if (low > high) return -1;
        int middle = low + (high - low) / 2;
        // guess middle
        boolean isItHotterFirstHalf = isItHotter(lastGuess, middle, target);
        if (isItHotterFirstHalf && middle == target)return middle;
        // guess middle + 1;
        boolean isItHotterSecondHalf = isItHotter(middle, middle+1, target);
        if (middle + 1 == target) return middle + 1;
        else if (isItHotterSecondHalf)return binarySearch(target, middle + 1, middle + 2, high);
        else return binarySearch(target, middle + 1, low, middle);
    }
    private boolean isItHotter(int lastGuess, int currentGuess, int secret){
        if (currentGuess == secret){
            StdOut.println("Found it!");
            return true;
        }
        if (Math.abs(secret - currentGuess) < Math.abs(secret - lastGuess)){
            StdOut.println("Hotter - last guess: " + lastGuess + " hotter than current guess: " + currentGuess);
            return true;
        }else {
            StdOut.println("Colder - last guess: " + lastGuess + " colder than current guess: " + currentGuess);
            return false;
        }
    }

    public static void main(String[] args) {
        myStopwatch timer1 = new myStopwatch();
        Ex34_1 ex34_1 = new Ex34_1();
        StdOut.println("Hot or Cold: " + ex34_1.hotOrCold(10,3));
        StdOut.println("Hot or Cold: " + ex34_1.hotOrCold(20,12));
        StdOut.println("Hot or Cold: " + ex34_1.hotOrCold(10,11));
        //double timeCost = timer1.elapseTime();
        double timeCost = timer1.elapseTime();
        StdOut.println("Time cost: " + timeCost);
    }
}
