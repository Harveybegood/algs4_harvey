package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

public class Ex40_1 {
     class MoveToFront<Item>{
        private class Node{
            Item item;
            Node prev, next;
            Node(Item item, Node prev, Node next){
                this.item = item;
                this.prev = prev;
                this.next = next;
            }
            //Node(){this(null,null,null);}
            void insertAfter(Item item){
                Node node = new Node(item,this,this.next);
                if (next != null){next.prev = node;}
                next = node;
                //return node;
            }
            Item delete(){
                Item deletion = item;
                item = null;
                if (next != null){next.prev = prev;}
                if (prev != null){prev.next = next;}
                return deletion;
            }
        }

        private Node header = new Node(null, null,null);
        private Node tailer = new Node(null, null,null);

        private int size;
        public boolean isEmpty(){return size == 0;}
        {
            header.next = tailer;
            tailer.prev = header;
            header.prev = tailer.next = null;
        }
        public void read(Item item){
            Node same = search(item);
            if (same != null){
                header.insertAfter(same.delete());
            }
            else {header.insertAfter(item);}
            size++;
            StdOut.println(this + "  read: " + item);
        }
        public Node search(Item item){
            if (isEmpty())
            {return null;}
            Node current = header.next;
            while (current != tailer){
                if (current.item.equals(item)){return current;}
                current = current.next;
            }
            return null;
        }
        public String toString(){
            if (isEmpty())return "[empty]";
            StringBuilder sc = new StringBuilder();
            Node current = header.next;
            while (current.next != tailer){
                sc.append(current.item + " -> ");
                current =current.next;
            }
            sc.append(current.item);
            return sc.toString();
        }
    }

    public static void main(String[] args) {
         Ex40_1 ex40_1 = new Ex40_1();
        MoveToFront<String> moveToFront = ex40_1.new MoveToFront<>();
        moveToFront.read("a");
        moveToFront.read("b");
        moveToFront.read("c");
        moveToFront.read("c");
        moveToFront.read("d");
        moveToFront.read("b");
    }
}
