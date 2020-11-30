package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Dynamic growth. Using linked lists or a resizing array, develop a weighted quick-union implementation that removes
*   the restriction on needing the number of objects ahead of time. Add a method newSite() to the API, which returns an
*   int identifier.
*
* */
public class Ex1_5_20 {
    private class dynamicWeightedQuickUnion{
        private int[] leader;
        private int[] size;
        private int numberOfSites;
        private int count;
        public int count(){return count;}
        public int getNumberOfSites(){return numberOfSites;}
        public boolean connected(int site1, int site2){
            if (leader == null || site1 >= numberOfSites || site2 >= numberOfSites){
                throw new RuntimeException("Site does not exist");
            }
            return find(site1) == find(site2);
        }
        public int find(int site){
            if (leader == null || site >= numberOfSites){
                throw new RuntimeException("Site does not exist");
            }
            while (site != leader[site]){
                site = leader[site];
            }
            return site;
        }
        public void union(int site1, int site2){
            if (leader == null || site1 >= numberOfSites || site2 >= numberOfSites){
                throw new RuntimeException("Site does not exist");
            }
            int leader1 = leader[site1];
            int leader2 = leader[site2];
            if (leader1 == leader2) return;
            if (size[leader1] < size[leader2]){
                leader[leader1] = leader2;
                size[leader2] += size[leader1];
            }else {
                leader[leader2] = leader1;
                size[leader1] += size[leader2];
            }
            count--;
        }
        public int newSite(){
            if (leader == null){
                leader = new int[1];
                size = new int[1];
            }if (numberOfSites == leader.length){
                resizeArray(numberOfSites * 2);
            }
            int newSiteId = numberOfSites;
            leader[newSiteId] = newSiteId;
            size[newSiteId] = 1;
            numberOfSites++;
            count++;
            return newSiteId;
        }
        private void resizeArray(int newSize){
            int[] newLeaderArray = new int[newSize];
            int[] newSizeArray = new int[newSize];
            for (int i = 0; i < numberOfSites; i++){
                newLeaderArray[i] = leader[i];
                newSizeArray[i] = size[i];
            }
            leader = newLeaderArray;
            size = newSizeArray;
        }
    }

    public static void main(String[] args) {
        Ex1_5_20 dynamicGrowth = new Ex1_5_20();
        dynamicWeightedQuickUnion resizeArrayWeightedQuickUnion = dynamicGrowth.new dynamicWeightedQuickUnion();
        resizeArrayWeightedQuickUnion.newSite();
        resizeArrayWeightedQuickUnion.newSite();
        resizeArrayWeightedQuickUnion.newSite();
        resizeArrayWeightedQuickUnion.newSite();
        resizeArrayWeightedQuickUnion.newSite();
        while (resizeArrayWeightedQuickUnion.count() != 1){
            int randomSite1 = StdRandom.uniform(resizeArrayWeightedQuickUnion.getNumberOfSites());
            int randomSite2 = StdRandom.uniform(resizeArrayWeightedQuickUnion.getNumberOfSites());
            if (!resizeArrayWeightedQuickUnion.connected(randomSite1, randomSite2)){
                resizeArrayWeightedQuickUnion.union(randomSite1, randomSite2);
                StdOut.println("United sites " + randomSite1 + " and " +  randomSite2);
            }
        }
    }
}
