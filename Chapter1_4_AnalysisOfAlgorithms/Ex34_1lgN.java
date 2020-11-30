package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

public class Ex34_1lgN {
    private int hotOrCold(int number, int target){
        return firstGuesses(number, target, 1, number);
    }
    private int firstGuesses(int number, int target, int low, int high){
        // Check if it is in the first half
        int firstGuessIndex = number / 2;
        if (firstGuessIndex == target){
            StdOut.println("Found it!");
            return firstGuessIndex;
        }
        // Check if it is in the second half
        int secondGuessIndex = number / 2 + 1;
        if (secondGuessIndex == target){
            StdOut.println("Found it!");
            return secondGuessIndex;
        }else {
            boolean isHotter = isHotter(firstGuessIndex, secondGuessIndex, target);
            if (isHotter){
                // Secret is in the second half, so the next guess will be "high"
                return initialSearch(target, secondGuessIndex, false, secondGuessIndex, high);
            }else {
                // Secret is in the first half, so the next guess will be "low"
                return initialSearch(target, secondGuessIndex, true, low, firstGuessIndex);
            }
        }
    }

    //This is just in case we fall into one of the 2 "end quarters" and need to go to one of the 2 "middle quarters"
    private int initialSearch(int target, int lastGuess, boolean isNextGuessInLeftEnd, int low, int high){
        if (low > high) return -1;
        int nextGuess;
        if (isNextGuessInLeftEnd) nextGuess = low;
        else nextGuess = high;
        boolean isHotter = isHotter(lastGuess, nextGuess, target);
        int middle = low + (high - low) / 2;
        if (nextGuess == target) return nextGuess;
        else if (isHotter){
            // We are in one of the 2 end quarters
            int middleOfMiddle;
            if (isNextGuessInLeftEnd){
                middleOfMiddle = low + (middle - low) / 2;
            }else {
                middleOfMiddle = middle + (high - middle) / 2;
            }
            // Guess middleOfMiddle + 1;
            isHotter = isHotter(middleOfMiddle, middleOfMiddle + 1, target);
            if (middleOfMiddle + 1 == target) return middleOfMiddle + 1;
            if (isHotter){
                // Secret is in the second half
                return search(target, middleOfMiddle, middleOfMiddle + 1, high);
            }else {
                return search(target, middleOfMiddle + 1, low, middleOfMiddle);
            }
        }else {
            // We are in one of the 2 middle quarters
            if (isNextGuessInLeftEnd){
                return search(target, nextGuess, middle + 1, high);
            }else {
                return search(target, nextGuess, low, middle);
            }
        }
    }

    private int search(int target, int lastGuess, int low, int high){
        if (low == high){
            if (low == target) return low;
            else return -1;
        }
        if (low > high) return -1;
        int nextGuess = low + high - lastGuess;
        boolean isHotter = isHotter(lastGuess, nextGuess, target);
        int middle = low + (high - low) / 2;
        if (nextGuess == target) return nextGuess;
        else if (isHotter){
            if (nextGuess >= high) return search(target, nextGuess, middle + 1, high);
            else return search(target, nextGuess, low, middle);
        }else {
            if (nextGuess >= high) return search(target, nextGuess, low, middle);
            else return search(target, nextGuess, middle + 1, high);
        }
    }
    private boolean isHotter(int lastGuess, int currentGuess, int secret){
        if (currentGuess == secret){
            StdOut.println("Found it!");
            return true;
        }else if (Math.abs(secret - currentGuess) < Math.abs(secret-lastGuess)){
            StdOut.println("Hotter -- currentGuess: " + currentGuess + " hotter than " + lastGuess);
            return true;
        }else {
            StdOut.println("Colder -- currentGuess: " + currentGuess + " colder than " + lastGuess);
            return false;
        }
    }

    public static void main(String[] args) {
        Ex34_1lgN ex34_1lgN = new Ex34_1lgN();
        StdOut.println("Hor or Cold: " + ex34_1lgN.hotOrCold(10, 3));
        StdOut.println("Hor or Cold: " + ex34_1lgN.hotOrCold(20, 12));
        StdOut.println("Hor or Cold: " + ex34_1lgN.hotOrCold(10, 11));
    }
}
