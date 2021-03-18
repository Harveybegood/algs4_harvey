package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

/*
*   Throwing eggs from a building. Suppose that you have an N-story building and plenty of eggs. Suppose also that an egg
*   is broken if it is thrown off floor F or higher, and unhurt otherwise. First, devise a strategy to determine the value
*   of F such that the number of broken eggs is ~lgN when using ~lgN throws, then find a way to reduce the cost to ~2lgF when
*   N is much larger than F.
*   Hint: binary search, repeated doubling and binary search
*   I copy a solution from the link as below
*   https://github.com/Harveybegood/algorithms-sedgewick-wayne/blob/master/src/chapter1/section4/Exercise24_ThrowingEggs.java
*
* */
public class Ex24_ThrowingEggsFromABuilding {

    public static void main(String[] args) {
        Ex24_ThrowingEggsFromABuilding throwingEggsFromABuilding = new Ex24_ThrowingEggsFromABuilding();
        int[] floors = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
        int floor = throwingEggsFromABuilding.findFloorInLgN(floors);
        StdOut.println("Floor: " + floor);
        int[] lotsOfFloors = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int floor2 = throwingEggsFromABuilding.findFloorIn2LgN(lotsOfFloors);
        StdOut.println("Floor: " + floor2);
    }

    private int findFloorInLgN(int[] floors){
        int low = 0;
        int high = floors.length - 1;
        return findFloorInLgN(floors, low, high);
    }
    private int findFloorInLgN(int[] floors, int low, int high){
        int key = 1;
        if (low <= high){
            int middle = low + (high - low) / 2;
            //StdOut.println("Debug current index: " + middle);
            if (key > floors[middle]){
                return findFloorInLgN(floors, middle + 1, high);
            }
            else {
                int lowerFloor = findFloorInLgN(floors, low, middle - 1);
                if (lowerFloor == -1){
                    return middle;
                }
                else {
                    return lowerFloor;
                }
            }
        }
        return -1;
    }

    private int findFloorIn2LgN(int[] floors){
        int low = 0;
        int high = floors.length - 1;
        return findFloorIn2LgN(floors, low, high, 0);
    }
    private int findFloorIn2LgN(int[] floors, int low, int high, int searchLevel){
        int key = 1;
        if (low <= high){
            int searchElement;
            if (searchLevel == 0){
                searchElement = 1;
            }
            else {
                searchElement = searchLevel * 2;
            }
            StdOut.println("Debug current index: " + searchElement);
            if (key > floors[searchElement]){
                return findFloorIn2LgN(floors, searchElement + 1, high, ++searchLevel);
            }
            else {
                int indexWhereWeDidNotFindElement = searchLevel / 2;
                int newFloor = findFloorInLgN(floors, indexWhereWeDidNotFindElement, searchElement - 1);
                if (newFloor == -1){
                    return searchElement;
                }
                else {
                    return newFloor;
                }
            }
        }
        return -1;
    }
}
