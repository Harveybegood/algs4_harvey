package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdOut;

/*
*   Create a data type Version that represents a software version number, such as 115.1.1, 115.10.1, 115.10.2.
*   Implement the Comparable interface so that 115.1.1 is less than 115.10.1, and so forth.
*
* */
public class Ex10_typeVersion implements Comparable<Ex10_typeVersion> {
    /*private final int hundreds;
    private final int tens;
    private final int units;
    public Ex10_typeVersion(int hun, int ten, int unit){
        hundreds = hun; tens = ten; units = unit;
    }
    public String toString(){
        return hundreds + "." + tens + "." + units;
    }
    public int getHundreds(){return hundreds;}
    public int getTens(){return tens;}
    public int getUnits(){return units;}*/
    private String typeVersion;
    public Ex10_typeVersion(String version){
        String[] versionSplit = version.split("\\.");
        typeVersion = version;
    }
    public int compareTo(Ex10_typeVersion that){
        String[] splitVersion = typeVersion.split("\\.");
        String[] otherSplitVersion = that.typeVersion.split("\\.");
        if (Integer.parseInt(splitVersion[0]) > Integer.parseInt(otherSplitVersion[0])){
            return 1;
        }
        else if (Integer.parseInt(splitVersion[0]) < Integer.parseInt(otherSplitVersion[0])){
            return -1;
        }
        else if (Integer.parseInt(splitVersion[1]) > Integer.parseInt(otherSplitVersion[1])){
            return 1;
        }
        else if (Integer.parseInt(splitVersion[1]) < Integer.parseInt(otherSplitVersion[1])){
            return -1;
        }
        else if (Integer.parseInt(splitVersion[2]) > Integer.parseInt(otherSplitVersion[2])){
            return 1;
        }
        else if (Integer.parseInt(splitVersion[2]) < Integer.parseInt(otherSplitVersion[2])){
            return -1;
        }
        else
            return 0;
    }

    public String toString() {
        return typeVersion;
    }

    public static void main(String[] args) {
        String version1 = "115.1.1";
        String version2 = "115.10.1";
        String version3 = "115.10.2";
        Ex10_typeVersion typeVersion1 = new Ex10_typeVersion(version1);
        Ex10_typeVersion typeVersion2 = new Ex10_typeVersion(version2);
        Ex10_typeVersion typeVersion3 = new Ex10_typeVersion(version3);
        if (typeVersion1.compareTo(typeVersion2) < 0){
            StdOut.println(typeVersion1 + " is less than " + typeVersion2);
        }
        else {
            StdOut.println(typeVersion1 + " is more than " + typeVersion2);
        }
        if (typeVersion2.compareTo(typeVersion3) < 0){
            StdOut.println(typeVersion2 + " is less than " + typeVersion3);
        }
        else {
            StdOut.println(typeVersion2 + " is more than " + typeVersion3);
        }
    }
}
