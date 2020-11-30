package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

/*
    Implement a nested class DoubleNode for building doubly-linked lists, where each node contains a reference to the item
*   preceding it and the item following it in the list(null if there is no such item)
*   insert at the beginning, insert at the end,
*   remove from the beginning, remove from the end,
*   insert before a given node, insert after a given node,
*   and remove a given node
* */
public class Ex31_back {
    private static class DoubleNode<Item>{
        Item item;
        DoubleNode<Item> next;
        DoubleNode<Item> prev;
        public DoubleNode(Item item, DoubleNode<Item> prev, DoubleNode<Item> next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public DoubleNode(Item item){
            new DoubleNode<Item>(item,null,null);
        }

        public DoubleNode<Item> insertAfter(Item item){
            DoubleNode<Item> node = new DoubleNode<>(item, this, this.next);
            if (next != null){
                next.prev = node;
                //next = node;
                //return node;
            }
            next = node;
            return node;
        }
        public DoubleNode<Item> insertBefore(Item item){
            DoubleNode<Item> node = new DoubleNode<>(item, this.prev, this);
            if (prev != null){
                prev.next = node;
                //prev = node;
                //return node;
            }
            prev = node;
            return node;
        }
        public Item delete(){
            Item del = item;
            item = null;
            if (next != null){next.prev = prev;}
            if (prev != null){prev.next = next;}
            return del;
        }
        // search a given node
        public DoubleNode<Item> search(Item item){
            DoubleNode<Item> current = this;
            while (current.prev != null){current = current.prev;}
            while (current != null){
                if (current.item.equals(item)){return current;}
                else {current = current.next;}
            }
            return null;
        }
        // insert after a given node
        public static <Item> void insertAfter(DoubleNode<Item> node, Item item) {
            node.insertAfter(item);
        }
        // insert before a given node
        public static <Item> void insertBefore(DoubleNode<Item> node, Item item){
            node.insertBefore(item);
        }
        // insert at the beginning of node
        public static <Item> void insertAtBeginning(DoubleNode<Item> node, Item item){
            while (node.prev != null){
                node = node.prev;
                node.insertBefore(item);
            }
        }
        // insert at the end of node
        public static <Item> void insertAtEnd(DoubleNode<Item> node, Item item){
            while (node.next != null){
                node = node.next;
                node.insertAfter(item);
            }
        }
        // remove from the beginning of node
        public static <Item> Item removeFromBeginning(DoubleNode<Item> node){
            while (node.prev != null){
                node = node.prev;
            }
            return node.delete();
        }
        // remove from the end of node
        public static <Item> Item removeFromEnd(DoubleNode<Item> node){
            while (node.next != null){
                node = node.next;
            }
            return node.delete();
        }
        // remove a given node
        public static <Item> Item remove(DoubleNode<Item> random, Item item){
            DoubleNode<Item> node = random.search(item);
            if (node == null){return null;}
            return node.delete();
        }
        public static <Item> Item remove(DoubleNode<Item> random, DoubleNode<Item> node){
            return remove(random, node.item);
        }
        void print(){
            DoubleNode<Item> current = this;
            while (current.prev != null){current = current.prev;}
            while (current.next != null){
                StdOut.print(current.item + " - ");
                current = current.next;
            }
            StdOut.println(current.item);
        }
    }

    public static void main(String[] args) {
        DoubleNode<Integer> list = new DoubleNode<>(0);
        list.insertAfter(1).insertAfter(2).insertAfter(3).insertAfter(4).insertAfter(5).
                insertAfter(6).insertAfter(7).insertAfter(8).insertAfter(9);
        StdOut.println("Print double linked list.");
        list.print();
        StdOut.println("\ninsert as first node");
        DoubleNode.insertAtBeginning(list,99);
        list.print();
        StdOut.println("\ninsert as first node");
        DoubleNode.insertAtBeginning(list,98);
        list.print();
        StdOut.println("\ninsert as first node");
        DoubleNode.insertAtBeginning(list,97);
        StdOut.println("\ninsert at last node");
        DoubleNode.insertAtEnd(list,96);
        list.print();
        StdOut.println("\ninsert at last node");
        DoubleNode.insertAtEnd(list,95);
        list.print();
        StdOut.println("\ninsert at lat node");
        DoubleNode.insertAtEnd(list, 94);
        list.print();

        DoubleNode<Integer> node = list.search(8);
        StdOut.println("\ninsert 93 after node " + node.item);
        DoubleNode.insertAfter(node,93);
        list.print();
        StdOut.println("\ninsert 92 before node " + node.item);
        DoubleNode.insertBefore(node,92);
        list.print();

        StdOut.println("\ndelete first node ");
        DoubleNode.removeFromBeginning(list);
        list.print();
        StdOut.println("\ndelete last node ");
        DoubleNode.removeFromEnd(list);
        list.print();

        StdOut.println("\ndelete node value of " + node.item);
        DoubleNode.remove(list,node);
        list.print();
    }
}
