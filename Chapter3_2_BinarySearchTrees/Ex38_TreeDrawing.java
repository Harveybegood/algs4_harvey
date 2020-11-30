package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/*
*   Tree drawing. Add a method draw() to BST that draws BST figures in the style of the text. Hint: Use instance variables
*   to hold node coordinates, and use a recursive method to set the values of these variables.
*
* */
public class Ex38_TreeDrawing<Key extends Comparable<Key>, Value> {
    private class Node{
        private Key key;
        private Value value;
        private Node left, right;
        private int countOfTree;
        private double Xcoordinate, Ycoordinate;
        public Node(Key key, Value value, int countOfTree){
            this.key = key;
            this.value = value;
            this.countOfTree = countOfTree;

        }
    }
    private Node root;
    private int treeLevel;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null)
            return 0;
        return node.countOfTree;
    }

    public void put(Key key, Value value){
        root = put(root, key, value);
    }
    private Node put(Node node, Key key, Value value){
        if (node == null)
            return new Node(key, value, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = put(node.left, key, value);
        else if (cmp > 0)
            node.right = put(node.right, key, value);
        else
            node.value = value;
        node.countOfTree = size(node.left) + size(node.right) + 1;
        return node;
    }
    public Value get(Key key){
        return get(root, key).value;
    }
    private Node get(Node node, Key key){
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            return get(node.left, key);
        else if (cmp > 0)
            return get(node.right, key);
        else
            return node;
    }
   /*public void drawTree(){
        StdDraw.setXscale(0, 150);
        StdDraw.setYscale(0, 150);
        double originalX = 75;
        double originalY = 130;
        StdDraw.setPenColor(Color.red);
        StdDraw.setPenRadius(.02);
        StdDraw.point(originalX, originalY);
        Point2D point2D = new Point2D(originalX, originalY);
        Queue<Node> queue = new Queue<>();
        drawTree(root, queue, point2D, originalX, originalY);
    }*/
   /*private void drawTree(Node node, Queue<Node> queue, Point2D point2D, double originalX, double originalY){
        int i = root.countOfTree;
        queue.enqueue(node);
        while (node != null && i > 0){
            if (!queue.isEmpty()){
                node = queue.dequeue();
                StdOut.println(node.key + " ");
                i--;
            }
            if (node.left != null){
                Point2D temp1 = point2D;
                originalX -= 10;
                originalY -= 10;
                double x1 = originalX;
                double y1 = originalY;
                StdDraw.setPenRadius(0.02);
                StdDraw.setPenColor(Color.red);
                StdDraw.point(x1, y1);
                Point2D point2D1 = new Point2D(x1, y1);
                StdDraw.setPenColor(Color.BLUE);
                StdDraw.setPenRadius(.005);
                temp1.drawTo(point2D1);
                temp1 = point2D1;
                //temp = point2D1;
                queue.enqueue(node.left);
                drawTree(node.left, queue, temp1, originalX, originalY);
            }
            if (node.right != null){
                Point2D temp2 = point2D;
                originalX += 20;
                //originalY = 10;
                StdDraw.setPenRadius(0.02);
                StdDraw.setPenColor(Color.red);
                StdDraw.point(originalX, originalY);
                Point2D point2D2 = new Point2D(originalX, originalY);
                StdDraw.setPenRadius(.005);
                StdDraw.setPenColor(Color.BLUE);
                temp2.drawTo(point2D2);
                temp2 = point2D2;
                queue.enqueue(node.right);
                drawTree(node.right, queue, temp2, originalX, originalY);
            }
        }
    }*/

   private void draw(){
       treeLevel = 0;
       setCoordinates(root, 0.9);
       //double nodeRadius = .025;
       //StdDraw.setPenColor(StdDraw.BLACK);
       drawLines(root);
       drawNodes(root);
   }
   private void setCoordinates(Node node, double distance){
       if (node == null)
       {
           return;
       }
       setCoordinates(node.left, distance - .05);
       node.Xcoordinate = (.5 + treeLevel++) / size();
       node.Ycoordinate = distance - .05;
       //drawLines(node);
       //drawNodes(node);
       setCoordinates(node.right, distance - .05);
   }
   private void drawLines(Node node){
       if (node == null){
           return;
       }
       drawLines(node.left);
       //StdDraw.setPenColor(StdDraw.BLACK);
       if (node.left != null){
           StdDraw.line(node.Xcoordinate, node.Ycoordinate, node.left.Xcoordinate, node.left.Ycoordinate);
       }
       if (node.right != null){
           StdDraw.line(node.Xcoordinate, node.Ycoordinate, node.right.Xcoordinate, node.right.Ycoordinate);
       }
       drawLines(node.right);
   }
   private void drawNodes(Node node){
       if (node == null){
           return;
       }
       double nodeRadius = .025;
       drawNodes(node.left);
       StdDraw.setPenColor(StdDraw.WHITE);
       StdDraw.filledCircle(node.Xcoordinate, node.Ycoordinate, nodeRadius);

       StdDraw.setPenColor(StdDraw.BLACK);
       StdDraw.circle(node.Xcoordinate, node.Ycoordinate, nodeRadius);
       StdDraw.text(node.Xcoordinate, node.Ycoordinate, String.valueOf(node.key));

       drawNodes(node.right);
   }


   public static void main(String[] args) {
       //StdDraw.setPenRadius(0.002);
       Ex38_TreeDrawing<String, Integer> treeDrawing = new Ex38_TreeDrawing<>();
       treeDrawing.put("S", 6);
       treeDrawing.put("E", 2);
       treeDrawing.put("X", 7);
       treeDrawing.put("A", 1);
       treeDrawing.put("R", 5);
       treeDrawing.put("H", 3);
       treeDrawing.put("M", 4);
        //StdOut.println(treeDrawing.size());
       StdOut.println(treeDrawing.get("S"));
       StdOut.println(treeDrawing.get("M"));
       StdDraw.clear(StdDraw.WHITE);
       treeDrawing.draw();
    }
}
