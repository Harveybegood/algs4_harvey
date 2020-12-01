package Chapter1_3_Bags_Queues_Stacks;
/*
*   Stack generality. Suppose that we have a sequence of intermixed push and pop operations as with our
*   test client, where the integer 0, 1 ..., N-1 in that order(push directive)
*   are intermixed with N minus signs(pop directives). Devise an algorithm that determines whether the intermixed sequence
*   causes the stack to underflow. (You may use only an amount of space independent of N - you cannot store
*   the integers in a data structure.) Devise a linear-time algorithm that determines whether a given
*   permutation can be generated as output by our test client(depending on where the pop operations occur).
*       Solution: The stack does not underflow unless there exists an integer k such that the first k pop
*       operations occur before the first k push operations.
*       if a given permutation can be generated, it is uniquely generated as follows: if the next integer in
*       the output permutation is in the top of the stack, pop it; otherwise, push the next integer in the input
*       sequence onto the stack(or stop if N-1 has already been pushed). The permutation can be generated
 *      if and only if the stack is empty upon termination.
*
* */
//https://github.com/reneargento/algorithms-sedgewick-wayne/blob/master/src/chapter1/section3/Exercise45_StackGenerability.java
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*
*
*
* */
public class Ex45_StackGenerability {
    private static boolean willTheStackUnderflow(String[] inputValues){
        int itemsCount = 0;
        for (String input : inputValues){
            if (input.equals("-")){itemsCount--;}
            else {itemsCount++;}
        }
        if (itemsCount < 0) {
            return false;
        }
        else {
            return true;
        }
    }
    private static boolean canAPermutationBeGenerated(String[] permutation){
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        for (String value : permutation){
            int integerValue = Integer.parseInt(value);
            if (stack.isEmpty() || integerValue > stack.peek()){
                while (current < integerValue){
                    stack.push(current);
                    current++;
                }
                current++;
            }else if (integerValue == stack.peek()){
                stack.pop();
                current++;
            }else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String inputValues = "0 1 2 - - - -";
        String[] input1 = inputValues.split("\\s");
        StdOut.println(willTheStackUnderflow(input1) + " Expected: false");
        String inputValues2 = "0 1 2 - - - 3 4 5 - - 6 7- - -";
        String[] input2 = inputValues2.split("\\s");
        StdOut.println(willTheStackUnderflow(input2) + " Expected: true");

        StdOut.println("-------------------------------------");
        StdOut.print("Input: 4 3 2 1 0 9 8 7 6 5 - ");
        StdOut.println(canAPermutationBeGenerated("4 3 2 1 0 9 8 7 6 5".split(" ")) + " Expected: true");
        StdOut.print("Input: 4 6 8 7 5 3 2 9 0 1 - ");
        StdOut.println(canAPermutationBeGenerated("4 6 8 7 5 3 2 9 0 1".split(" ")) + " Expected: false");
    }
}
