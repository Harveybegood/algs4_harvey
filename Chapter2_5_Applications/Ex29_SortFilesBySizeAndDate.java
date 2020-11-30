package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
*   Sort files by size and date of last modification. Write comparators for the type File to order by increasing/decreasing
*   order of file size, ascending/descending order of file name, and ascending/descending order of last modification data.
*   Use these comparators in a program LS that takes a command-line argument and lists the files in the current directory
*   according to a specified order, e.g., "-t" to sort by timestamp. Support multiple flags to break ties. Be sure to use
*   a stable sort.
*
* */
public class Ex29_SortFilesBySizeAndDate {
    public static void main(String[] args) {
        Ex29_SortFilesBySizeAndDate sortFilesBySizeAndDate = new Ex29_SortFilesBySizeAndDate();
        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString();
        File directory = new File(path);
        //Input example: -s -d -n -t
        File[] sortedFile = sortFilesBySizeAndDate.readFlagsAndSortFiles(directory, args);
        if (sortedFile == null){
            return;
        }
        for (File file : sortedFile){
            StdOut.println(file);
        }
    }
    private File[] readFlagsAndSortFiles(File directory, String[] flags){
        File[] filesInCurrentDirectory = directory.listFiles();
        if (filesInCurrentDirectory == null){
            return null;
        }
        List<File> allFiles = new ArrayList<>();
        for (File file : filesInCurrentDirectory){
            if (!file.isDirectory()){
                allFiles.add(file);
            }
        }
        File[] files = new File[allFiles.size()];
        allFiles.toArray(files);
        for (int i = flags.length - 1; i >= 0; i--){
            String flag = flags[i];
            boolean isDecreasing = flag.equals("-d");
            if (isDecreasing && i > 0){
                i--;
                flag = flags[i];
            }
            else if (isDecreasing){
                throw new IllegalArgumentException("Missing flag (-s -t or -n)");
            }
            switch (flag){
                case "-s":
                    if (!isDecreasing){
                        Arrays.sort(files, fileSizeIncreasingComparator());
                    }
                    else
                        Arrays.sort(files, fileSizeDecreasingComparator());
                    break;
                case "-t":
                    if (!isDecreasing){
                        Arrays.sort(files, fileNameIncreasingComparator());
                    }
                    else {
                        Arrays.sort(files, fileNameDecreasingComparator());
                    }
                    break;
                case "-n":
                    if (!isDecreasing){
                        Arrays.sort(files, fileModifiedDateIncreasingComparator());
                    }
                    else{
                        Arrays.sort(files, fileModifiedDateDecreasingComparator());
                    }
                    break;
                    default:
                        throw new IllegalArgumentException("Invalid flag, the only accepted flags are: -s -t -n -d");
            }
        }
        return files;
    }
    /*public class fileSize implements Comparator<LS>{
        public int compare(LS p, LS q){
            if (p.fileSize < q.fileSize)
                return -1;
            else if (p.fileSize > q.fileSize)
                return 1;
            else return 0;
        }
    }
    public Comparator<LS> fileSize(){return new fileSize();}

    public class fileName implements Comparator<LS>{
        public int compare(LS p, LS q){
            if (p.fileName.compareTo(q.fileName) < 0)
                return -1;
            else if (p.fileName.compareTo(q.fileName) > 0)
                return 1;
            else return 0;
        }
    }
    public Comparator<LS> fileName(){return new fileName();}

    public class fileDate implements Comparator<LS>{
        public int compare(LS p, LS q){
            if (p.date.compareTo(q.date) < 0)
                return -1;
            else if (p.date.compareTo(q.date) > 0)
                return 1;
            else return 0;
        }
    }
    public Comparator<LS> fileDate(){return new fileDate();}

    public class LS{
        private int fileSize;
        private String fileName;
        private Date date;
        public LS(int fileSize, String fileName, Date date){
            this.fileName = fileName;
            this.fileSize = fileSize;
            this.date = date;
        }
    }*/
    private Comparator<File> fileNameIncreasingComparator(){
        return new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int nameComparing = o1.getName().compareTo(o2.getName());
                if (nameComparing < 0) return -1;
                else if (nameComparing > 0) return 1;
                else return 0;
            }
        };
    }
    private Comparator<File> fileNameDecreasingComparator(){
        return new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int nameComparing = o1.getName().compareTo(o2.getName());
                if (nameComparing < 0) return 1;
                else if (nameComparing > 0) return -1;
                else
                    return 0;
            }
        };
    }
    private Comparator<File> fileSizeIncreasingComparator(){
        return new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                long size1 = o1.length();
                long size2 = o2.length();
                if (size1 < size2) return -1;
                else if (size1 > size2) return 1;
                return 0;
            }
        };
    }
    private Comparator<File> fileSizeDecreasingComparator(){
        return new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                long size1 = o1.length();
                long size2 = o2.length();
                if (size1 < size2) return 1;
                else if (size1 > size2) return -1;
                else
                return 0;
            }
        };
    }
    private Comparator<File> fileModifiedDateIncreasingComparator(){
        return new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                long lastModifiedDate1 = o1.lastModified();
                long lastModifiedDate2 = o2.lastModified();
                if (lastModifiedDate1 < lastModifiedDate2)
                    return -1;
                else if (lastModifiedDate1 > lastModifiedDate2)
                    return 1;
                else
                return 0;
            }
        };
    }
    private Comparator<File> fileModifiedDateDecreasingComparator(){
        return new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                long lastModifiedDate1 = o1.lastModified();
                long lastModifiedDate2 = o2.lastModified();
                if (lastModifiedDate1 < lastModifiedDate2)
                    return 1;
                else if (lastModifiedDate1 > lastModifiedDate2)
                    return -1;
                else
                return 0;
            }
        };
    }
}
