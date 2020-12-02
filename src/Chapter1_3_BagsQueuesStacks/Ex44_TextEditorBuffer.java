package Chapter1_3_BagsQueuesStacks;


import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*
*   Text editor buffer. Develop a data type for a buffer in a text editor that implements the following API:
*
*       Hint: Use two stacks.
*
* */
@SuppressWarnings("unchecked")
public class Ex44_TextEditorBuffer {

    // how to initialize the cursor
    public static class Buffer{
        Stack<Character> stack1;
        Stack<Character> stack2;

        public Buffer(){
           stack1 = new Stack<>();
           stack2 = new Stack<>();

        }
        // insert c at the cursor position
        public void insert(char c){
            popOutHelper(stack1, stack2, 3);
            stack1.push('c');
            pushInHelpder(stack1, stack2);
        }
        // delete and return the character at the cursor
        public char delete(){
            Character item;
            popOutHelper(stack1, stack2, 3);
            item = stack2.pop();
            pushInHelpder(stack1, stack2);
            return item;
        }
        // move the cursor k positions to the left
        public void left(int k){
            // suppose that current cursor at the position of count
            int count = 4;

        }
        // move the cursor k positions to the right
        public void right(int k){

        }
        // number of characters in the buffer
        public int size(){
            return stack1.size() + stack2.size();
        }
        // pop characters out, suppose that the int n stands for the position of cursor
        public void popOutHelper(Stack<Character> stack1, Stack<Character> stack2, int n){
            while (n > 0){
                stack2.push(stack1.pop());
                n--;
            }
        }
        // push characters back in
        public void pushInHelpder(Stack<Character> stack1, Stack<Character> stack2){
            while (!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
        }
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        buffer.stack1.push('a');
        buffer.stack1.push('b');
        buffer.stack1.push('c');
        buffer.stack1.push('d');
        buffer.stack1.push('e');
        StdOut.println("The size buffer: " + buffer.size());
    }
}




