package Chapter1_3_BagsQueuesStacks;
/*
*   Listing files. A folder is a list of files and folders. Write a program that takes the name of a folder as a command-line
*   argument and prints out all of the files contained in that folder, With the contents of each folder recursively listed
*   (indented) under that folder's name. Hint: Use a queue, and see java.io.File
*
* */
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
public class Ex43_ListingFiles {
    public static void main(String[] args) {
        Ex43_ListingFiles listingFiles = new Ex43_ListingFiles();
        String nameOfFolder = args[0];
        File files = new File(nameOfFolder);
        listingFiles.listingFile(files, 0);
        for (String fileName : listingFiles.queue){
            StdOut.println(fileName);
        }
    }
    Queue<String> queue = new Queue<>();
    //
    public void listingFile(File file, int depth){
        if (!file.exists()) {
            return;
        }
        addFileToQueue(file, depth);
        File[] fileList = file.listFiles();
        if (fileList != null){
            for (File fileItem : fileList){
                if (fileItem.isFile()){
                    addFileToQueue(fileItem, depth);
                }
                else if (fileItem.isDirectory()){
                    listingFile(fileItem, depth + 1);
                }
            }
        }
    }
    private void addFileToQueue(File file, int depth){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < depth; i++){
            stringBuilder.append(" ");
        }
        stringBuilder.append(file.getName());
        queue.enqueue(stringBuilder.toString());
    }
}
