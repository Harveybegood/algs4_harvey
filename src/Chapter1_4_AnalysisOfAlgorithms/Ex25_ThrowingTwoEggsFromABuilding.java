package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

/*
*   Throwing two eggs from a building. Consider the previous question, but now suppose you only have two eggs, and your cost
*   model is the number of throws. Devise a strategy to determine F such that the number of throws is at most 2√N, then find
*   a way to reduce the cost to ~c√F. This is analogous to a situation where search hits(egg intact)are much cheaper than
*   misses(egg broken).
*   Suppose that you have an N-story building and 2 eggs. Suppose also that an egg is broken if it is thrown off floor F or higher,
*   and unbroken otherwise. Your cost model is the number of throws. Devise a strategy to determine F such that the number of throws ~c √ F
*   for some constant c.
*
*   Hint for Part 2: 1 + 2 + 3 + ... k ~ 1/2 k^2
*
* */
public class Ex25_ThrowingTwoEggsFromABuilding{
    public static void main(String[] args) {
        int[] floors = new int[100];
        for (int i = 0; i < 100; i++){
            if (i < 76){
                floors[i] = 0;
            }
            else {
                floors[i] = 1;
            }
        }
        StdOut.println("Floors: " + throwEggs2SqrtN(floors));
        StdOut.println("Floors: " + throwEggsCSqrtF(floors));
    }
    // Determine F such that the number of throws is at most 2√N
    // on a basis of √N, which tells the number of throws are square root of height of floors. we shall double the number of throw
    public static int throwEggs2SqrtN(int[] floors){
        int key = 1;
        int high = floors.length - 1;
        int floorLevel = (int) Math.sqrt(high);
        while (floors[floorLevel] < key){
            floorLevel += (int) Math.sqrt(high);
        }
        int linearSearchFloor = floorLevel - (int) Math.sqrt(high);
        // in case that egg is broken while throwing egg at first time, egg probably will be broken at 0 floor.
        if (linearSearchFloor == 0){
            for (int i = linearSearchFloor; i < floorLevel; i++){
                if (floors[i] < key){
                    continue;
                }
                return i;
            }
        }
        //
        for (int i = linearSearchFloor + 1; i < floorLevel; i++){
            if (floors[i] < key){
                continue;
            }
            return i;
        }
        return -1;
    }

    // Reduce the cost to ~c√F
    public static int throwEggsCSqrtF(int[] floors){
        return throwEggsCSqrtF(floors, 0, 0);
    }
    private static int throwEggsCSqrtF(int[] floors, int throwingFloor, int increasingFloor){
        int key = 1;
        throwingFloor = throwingFloor + increasingFloor;
        if (floors[throwingFloor] < key){
            return throwEggsCSqrtF(floors, throwingFloor, ++increasingFloor);
        }
        else {
            int lastFloorNotBroken = throwingFloor - increasingFloor;
            while (floors[lastFloorNotBroken] < key){
                lastFloorNotBroken++;
            }
            return lastFloorNotBroken;
        }
        //return -1;
    }

}
