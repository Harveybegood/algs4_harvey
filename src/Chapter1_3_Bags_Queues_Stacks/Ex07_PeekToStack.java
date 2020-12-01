package Chapter1_3_Bags_Queues_Stacks;


import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("unchecked")
public class Ex07_PeekToStack {
    public static class Stack<Item>{
        private int n;
        private Item[] items;
        public Stack(int cap){
            n = 0;
            items = (Item[]) new Object[cap];
        }
        public boolean isEmpty(){return n == 0;}
        public int size(){return n;}
        public void push(Item item){
            if (n == items.length / 2){
                resize(2 * items.length);
            }
            items[n++] = item;
        }
        public Item pop(){
            Item item = items[n];
            n--;
            if (n == items.length / 4){
                resize(items.length / 2);
            }
            return item;
        }
        // Stack sticks to LIFO
        public Item peek(){
            return items[n - 1];
        }
        // resize
        public void resize(int newSize){
            Item[] aux = (Item[]) new Object[newSize];
            for (int i = 0; i < n; i++){
                aux[i] = items[i];
            }
            items = aux;
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack(2);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        StdOut.println(stack.peek() + "    expected with 4");
    }
}
