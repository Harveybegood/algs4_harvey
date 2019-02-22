package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;

/*
*   Priority queue with explicit links. Implement a priority queue using a heap-ordered binary tree, but use a triply
*   linked structure instead of an array. You will need three links per node: two to traverse down the tree and one to
*   traverse up the tree. Your implementation should guarantee logarithmic running time per operation, even if no maximum
*   priority-queue size is known ahead of time.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_4_24<Key extends Comparable<Key>> {
    private class PQNode{
        Key value;
        PQNode parent;
        PQNode leftChild;
        PQNode rightChild;
    }
    private class PQExplicitLinks{
        private PQNode pq;
        private int size = 0;
        PQExplicitLinks(){
            pq = new PQNode();
        }
        public boolean isEmpty(){return size == 0;}
        public int size(){return size;}
        public void insert(Key value){
            PQNode newNode = new PQNode();
            newNode.value = value;
            int parentIndex = size / 2;
            int[] pathToParentNode = generatePathToNode(parentIndex);
            PQNode parentNode = getNode(pathToParentNode);
            if (parentNode.leftChild == null){
                parentNode.leftChild = newNode;
            }else {
                parentNode.rightChild = newNode;
            }
            newNode.parent = parentNode;
            size++;
            swim(newNode);
        }
        public Key delMax(){
            if (size == 0){throw new RuntimeException("Queue is underflow. ");}
            Key max = pq.leftChild.value;
            int parentNodeIndex = size / 2;
            if (parentNodeIndex == 0){
                pq.leftChild = null;
                return max;
            }
            int[] pathToLastNodeParent = generatePathToNode(parentNodeIndex);
            PQNode lastNodeParent = getNode(pathToLastNodeParent);
            Key lastItemValue;
            if (lastNodeParent.rightChild != null){
                lastItemValue = lastNodeParent.rightChild.value;
                lastNodeParent.rightChild.parent = null;
                lastNodeParent.rightChild = null;
            }else {
                lastItemValue = lastNodeParent.leftChild.value;
                lastNodeParent.leftChild.parent = null;
                lastNodeParent.leftChild = null;
            }
            pq.leftChild.value = lastItemValue;
            sink(pq.leftChild);
            size--;
            return max;
        }
        public void swim(PQNode node){
            while (node.parent.value != null && node.parent.value.compareTo(node.value) < 0){
                Key temp = node.value;
                node.value = node.parent.value;
                node.parent.value = temp;
                node = node.parent;
            }
        }
        private void sink(PQNode node){
            boolean isTheLeftChildHighestItem;
            Key highestItemValue;
            while (node != null && node.leftChild != null){
                // check which child is bigger
                if (node.rightChild != null){
                    if (node.leftChild.value.compareTo(node.rightChild.value) < 0){
                        isTheLeftChildHighestItem = false;
                        highestItemValue = node.rightChild.value;
                    }else {
                        isTheLeftChildHighestItem = true;
                        highestItemValue = node.leftChild.value;
                    }
                }else {
                    isTheLeftChildHighestItem = true;
                    highestItemValue = node.leftChild.value;
                }
                // Compare highest value child and parent
                if (node.value.compareTo(highestItemValue) < 0){
                    Key temp = node.value;
                    if (isTheLeftChildHighestItem){
                        node.value = node.leftChild.value;
                        node.leftChild.value = temp;
                        node = node.leftChild;
                    }else {
                        node.value = node.rightChild.value;
                        node.rightChild.value = temp;
                        node = node.rightChild;
                    }
                }else {
                    break;
                }
            }
        }

        private int[] generatePathToNode(int nodeIndex){
            int height = (int) Math.ceil(Math.log10(nodeIndex) / Math.log10(2)) + 1;
            if (height <= 0){
                return new int[0];
            }
            int[] pathNode = new int[height];
            for (int i = height - 1; i >= 0; i--){
                pathNode[i] = nodeIndex;
                nodeIndex /= 2;
            }
            return pathNode;
        }
        private PQNode getNode(int[] pathNode){
            int currentIndex = 1;
            PQNode currentNode = pq.leftChild;
            for (int i = 0; i < pathNode.length && currentNode != null; i++){
                if (pathNode[i] == currentIndex * 2){
                    currentNode =  currentNode.leftChild;
                    currentIndex = currentIndex * 2;
                }else if (pathNode[i] == currentIndex * 2 + 1){
                    currentNode = currentNode.rightChild;
                    currentIndex = currentIndex * 2 + 1;
                }
            }
            if (currentNode == null){
                return pq;
            }
            return currentNode;
        }
    }

    public static void main(String[] args) {
        Ex2_4_24<Integer>.PQExplicitLinks pqExplicitLinks = new Ex2_4_24<Integer>().new PQExplicitLinks();
        StdOut.println("IsEmpty: " + pqExplicitLinks.isEmpty() + " Expected: true. ");
        pqExplicitLinks.insert(10);
        pqExplicitLinks.insert(8);
        pqExplicitLinks.insert(15);
        pqExplicitLinks.insert(2);
        StdOut.println("Size: " + pqExplicitLinks.size() + " Expected: 4. ");
        StdOut.println(pqExplicitLinks.delMax());
        StdOut.println(pqExplicitLinks.delMax());
        StdOut.println(pqExplicitLinks.delMax());
        StdOut.println("Size: " + pqExplicitLinks.size() + " Expected: 1. ");
    }
}
