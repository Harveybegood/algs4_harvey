package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

/*
*       Array --> Chapter1_3_Bags_Queues_Stacks.stack
*       LinkedList --> Chapter1_3_Bags_Queues_Stacks.stack
*       Stack<Item> t = new Stack<Item>(s)
* */
@SuppressWarnings("unchecked")
public class Ex42_1 {
    private class ArrayStack<Item>{
        Item[] items = (Item[]) new Object[1];
        private int size;
        private int counter = 0;
        private final int id = counter++;
        public boolean isEmpty(){return size == 0;}
        ArrayStack(){}
        ArrayStack(ArrayStack<Item> stack){
            this.size = stack.size;
            items = (Item[]) new Object[stack.items.length];
            for (int i = 0; i < items.length; i++){
                items[i] = stack.items[i];
            }
        }
        void resize(int newSize){
            Item[] temp = (Item[]) new Object[newSize];
            for (int i = 0; i < size; i++){
                temp[i] = items[i];
            }
            items = temp;
        }

        void push(Item item){
            if (size == items.length){resize(2*size);}
            items[size++] = item;
            StdOut.println(this);
        }
        Item pop(){
            if (isEmpty()){throw new RuntimeException("Chapter1_3_Bags_Queues_Stacks.stack underflow");}
            Item stack = items[size];
            size--;
            items[size] = null;
            if (size > 0 && size == items.length/4){resize(items.length/2);}
            StdOut.println(this);
            return stack;
        }
        public String toString(){
            if (isEmpty()){return "[Empty]";}
            StringBuilder stringB = new StringBuilder();
            stringB.append(String.format("Chapter1_3_Bags_Queues_Stacks.stack-%d",id));
            for (int i = 0; i < items.length; i++){
                stringB.append(String.format(" %3s |", items[i] == null ? " " : items[i]));
            }
            return stringB.toString();
        }
    }
    public void arrayStackImplementation(){
        ArrayStack<Integer> arraystack = new ArrayStack<>();
        for (int i = 0; i < 5; i++){
            arraystack.push(i);
        }
        for (int i = 0; i < 2; i++){
            arraystack.pop();
        }
        ArrayStack<Integer> arrayStack1 = new ArrayStack<>(arraystack);
        for (int i = 0; i < 2; i++){
            arrayStack1.pop();
        }
        for (int i = 0; i < 5; i++){
            arrayStack1.push(i);
        }
    }

    public static void main(String[] args) {
        Ex42_1 ex42_1 = new Ex42_1();
        ex42_1.arrayStackImplementation();
    }
}
