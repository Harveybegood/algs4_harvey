package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
*   Fast algorithms for Erdos-Renyi model. Add weighted quick-union and weighted quick-union with path compression to
*   your Test from exercise 1.5.23. Can you descern a difference between these two algorithms?
*
* */
public class Ex1_5_24 {
    public class Exercise13_WeightedQuickUnionPathCompression{
        public class WeightedQuickUnionPathCompression implements UF{
            private int[] id;
            private int[] size;
            private int count;
            public WeightedQuickUnionPathCompression(int size){
                id = new int[size];
                this.size = new int[size];
                count = size;
                for (int i = 0; i < id.length; i++){
                    id[i] = i;
                    this.size[i] = 1;
                }
            }
            public int count(){return count;}
            public int find(int p){
                if (p == id[p])return p;
                return id[p] = find(id[p]);
            }
            public boolean connected(int p, int q){
                return find(p) == find(q);
            }
            public void union(int p, int q){
                int pID = find(p);
                int qID = find(q);
                if (pID == qID) return;
                if (size[pID] < size[qID]){
                    id[pID] = qID;
                    size[qID] += size[pID];
                }else {
                    id[qID] = pID;
                    size[pID] += size[qID];
                }
                count--;
            }
        }
    }
    public interface UF{
        int count();
        int find(int p);
        boolean connected(int p, int q);
        void union(int p, int q);
    }
    public class WeightedQuickUnion implements UF{
        private int[] id;
        private int[] size;
        private int count;
        public WeightedQuickUnion(int size){
            id = new int[size];
            this.size = new int[size];
            count = size;
            for (int i = 0; i < id.length; i++){
                id[i] = i;
                this.size[i] = 1;
            }
        }
        public int count(){return count;}
        public int find(int p){
            while (p != id[p]) p = id[p];
            return p;
        }
        public boolean connected(int p, int q){return find(p) == find(q);}
        public void union(int p, int q){
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot)return;
            if (size[pRoot] < size[qRoot]){
                id[pRoot] = qRoot;
                size[qRoot] += size[pRoot];
            }else {
                id[qRoot] = pRoot;
                size[pRoot] += size[qRoot];
            }
            count--;
        }
        public int[] getSize(){return size;}
    }

    public class UnionFind implements UF{
        private int[] leader;
        private int[] ranks;
        private int components;
        public UnionFind(int size){
            leader = new int[size];
            ranks = new int[size];
            components = size;
            for (int i = 0; i < size; i++){
                leader[i] = i;
                ranks[i] = 0;
            }
        }
        public int count(){return components;}
        public boolean connected(int p, int q){return find(p) == find(q);}
        public int find(int p){
            if (p == leader[p]){
                return p;
            }
            return leader[p] = find(leader[p]);
        }
        public void union(int p, int q){
            int leader1 = find(p);
            int leader2 = find(q);
            if (leader1 == leader2)return;
            if (ranks[leader1] < ranks[leader2]){
                leader[leader1] = leader2;
            }else if (ranks[leader1] > ranks[leader2]){
                leader[leader2] = leader1;
            }else {
                leader[leader1] = leader2;
                ranks[leader2]++;
            }
            components--;
        }
    }


    public class Exercise34_RandomBag<Item> implements Iterable<Item>{
        private Item[] array;
        private int size;
        @SuppressWarnings("unchecked")
        public Exercise34_RandomBag(){
            array = (Item[]) new Object[1];
            size = 0;
        }
        public boolean isEmpty(){return size == 0;}
        public int size(){return size;}
        public void add(Item item){
            if (size() == array.length){
                resize(array.length * 2);
            }
            array[size] = item;
            size++;
        }
        @SuppressWarnings("unchecked")
        public void resize(int newSize){
            Item[] temp = (Item[]) new Object[newSize];
            for(int i = 0; i < array.length; i++){
                temp[i] = array[i];
            }
            array = temp;
        }

        @Override
        public Iterator<Item> iterator() {
            return new randomBagIterator();
        }
        private class randomBagIterator implements Iterator<Item>{
            int index;
            Item[] arrayCopy;
            @SuppressWarnings("unchecked")
            public randomBagIterator(){
                index = 0;
                arrayCopy = (Item[]) new Object[size];
                for(int i = 0; i < size; i++){
                    arrayCopy[i] = array[i];
                }
                sortArrayCopy();
            }
            public boolean hasNext(){return index < size();}
            public Item next(){
                Item item = arrayCopy[index];
                index++;
                return item;
            }
            private void sortArrayCopy(){
                for(int i = 0; i < size; i++){
                    int randomIndex = StdRandom.uniform(0, size - 1);
                    Item temp = arrayCopy[i];
                    arrayCopy[i] = arrayCopy[randomIndex];
                    arrayCopy[randomIndex] = temp;
                }
            }
        }

        /*public static void main(String[] args) {

        }*/
    }
    public class Exercise18_RandomGridGenerator{
        private class Connection{
            int p, q;
            public Connection(int p, int q){
                this.p = p;
                this.q = q;
            }
        }
        public Connection[] generated(int numberOfSites){
            Exercise34_RandomBag<Connection> randomBag = new Exercise34_RandomBag<>();
            for (int i = 0; i < numberOfSites; i++){
                for (int j = 0; j < numberOfSites; j++){
                    if (i != j){
                        Connection connection = new Connection(i, j);
                        randomBag.add(connection);
                    }
                }
            }
            Connection[] connections = new Connection[randomBag.size()];
            int index = 0;
            for (Connection connection : randomBag){
                connections[index] = connection;
                index++;
            }
            return connections;
        }
    }
    private class Experiment{
        int numberOfSites;
        double ratioBetweenUnionFindModel;
        public Experiment(int numberOfSites, double ratioBetweenUnionFindModel){
            this.numberOfSites = numberOfSites;
            this.ratioBetweenUnionFindModel = ratioBetweenUnionFindModel;
        }
    }

    private void doExperiments(int numberOfExperiments){
        List<Ex1_5_24.Experiment> experiments = new ArrayList<>();
        int numberOfSites = 512;
        for (int i = 0; i < numberOfExperiments; i++){
            UF weightedQuickUnion = new WeightedQuickUnion(numberOfSites);
            Stopwatch timer = new Stopwatch();
            List<Exercise18_RandomGridGenerator.Connection> connectionsGenerated =
                    erdosRenyiGeneratingConnection(numberOfSites,weightedQuickUnion);
            double runningTimeWeightedQuickUnion = timer.elapsedTime();
            UF weightedQuickUnionPathCompression = new Exercise13_WeightedQuickUnionPathCompression().
                    new WeightedQuickUnionPathCompression(numberOfSites);
            timer = new Stopwatch();
            erdosRenyiUsingConnections(weightedQuickUnionPathCompression, connectionsGenerated);
            double runningTimeWeightedQuickUnionPathCompression = timer.elapsedTime();
            Experiment experiment = new
                    Experiment(numberOfSites, runningTimeWeightedQuickUnionPathCompression/runningTimeWeightedQuickUnion);
            experiments.add(experiment);
            numberOfSites *= 2;
        }
        printResults(experiments);
    }

    private List<Exercise18_RandomGridGenerator.Connection> erdosRenyiGeneratingConnection
            (int numberOfSites, UF unionFind){
        List<Exercise18_RandomGridGenerator.Connection> connectionsGenerated = new ArrayList<>();
        while (unionFind.count() != 1){
            int randomSite1 = StdRandom.uniform(numberOfSites);
            int randomSite2 = StdRandom.uniform(numberOfSites);
            Exercise18_RandomGridGenerator.Connection connection = new Exercise18_RandomGridGenerator().new
                    Connection(randomSite1, randomSite2);
            connectionsGenerated.add(connection);
            if (!unionFind.connected(randomSite1, randomSite2)){
                unionFind.union(randomSite1, randomSite2);
            }
        }
        return connectionsGenerated;
    }

    private void erdosRenyiUsingConnections(UF unionFind, List<Exercise18_RandomGridGenerator.Connection>
            generatedConntions){
        int connectionIndex = 0;
        while (unionFind.count() != 1){
            Exercise18_RandomGridGenerator.Connection connection = generatedConntions.get(connectionIndex);
            if (!unionFind.connected(connection.p, connection.q)){
                unionFind.union(connection.p, connection.q);
            }
            connectionIndex++;
        }
    }

    private void printResults(List<Ex1_5_24.Experiment> experiments){
        StdOut.printf("%12S %17S %23S\n", "Experiment |", "Number of Sites |", "Ratio of Running Time |");
        int experimentID = 1;
        for (Experiment experiment : experiments){
            StdOut.printf("%7d %13d %23.1f\n", experimentID, experiment.numberOfSites, experiment.ratioBetweenUnionFindModel);
            experimentID++;
        }
    }

    public static void main(String[] args) {
        //int numberOfExperiments = Integer.parseInt(args[0]);
        int numberOfExperiments = 11;
        Ex1_5_24 compareTestErdosRenyi = new Ex1_5_24();
        compareTestErdosRenyi.doExperiments(numberOfExperiments);
    }
}
