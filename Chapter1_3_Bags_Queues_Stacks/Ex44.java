package Chapter1_3_Bags_Queues_Stacks;


import edu.princeton.cs.algs4.StdRandom;


/*
*   Text editor buffer. Develop a data type for a buffer in a text editor that implements the following API:
*   Hint: Use two stacks
*
* */
@SuppressWarnings("unchecked")
public class Ex44 {
    public class Buffer<Item>{
        Item[] bufferArray;
        private int size;
        private int head;
        private int tail;
        private int index;
        Buffer(){
            bufferArray = (Item[]) new Object[1];
            size = 0;
            head = 0;
            tail = 0;
            index = 0;
        }
        public boolean isEmpty(){return size == 0;}

        public void push(Item item){
            if (size == bufferArray.length){resize(2*bufferArray.length);}
            bufferArray[head++] = item;
            size++;
        }
        public Item pop(){
            if (isEmpty()){throw new RuntimeException("Buffer underflow");}
            Item item = bufferArray[size];
            bufferArray[size] = null;
            size--;
            if (size > 0 && size == bufferArray.length / 4){resize(bufferArray.length/2);}
            else {
                bufferArray[head] = bufferArray[tail];
                head = 0;
                tail = 0;
            }
            return item;
        }
        public void resize(int newSize){
            Item[] temp = (Item[]) new Object[newSize];
            for (int i = 0; i < size; i++){
                temp[i] = bufferArray[i];
            }
            bufferArray = temp;
         }
        void insert(Item c){
            if (size == bufferArray.length){resize(2*bufferArray.length);}
            index = StdRandom.uniform(0,size);
            bufferArray[index] = c;
            for (int i = index + 1; i < size; i++){
                bufferArray[i + 1] = bufferArray[i];
            }
            size++;
        }
        Item delete(){  // delete and return the character at the cursor
            index = StdRandom.uniform(0, size);
            Item item = bufferArray[index];
            bufferArray[index] = null;
            index--;
            for (int i = index + 1; i < size; i++){
                bufferArray[index] = bufferArray[index + 1];
            }
            return item;
        }
        void left(int k){
            if (k < 0 || k > size){throw new RuntimeException("Stack underflow");}
            int index = StdRandom.uniform(0, size);
            Item item = bufferArray[index];
        }
        void right(int k){
            if (k < 0 || k > size){throw new RuntimeException("Stack underflow");}
            int index = StdRandom.uniform(0, size);
            Item item = bufferArray[index];
        }
        int size(){return size;}
    }

    public static void main(String[] args) {

    }

}
