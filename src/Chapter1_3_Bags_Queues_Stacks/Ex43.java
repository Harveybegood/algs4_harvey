package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

/*
*   Listing files. A folder is a list of files and folders. Write a program that takes the name of a folder
*   as a command-line argument and prints out all of the files contained in that folders, with the contents
*   of each folder recursively listed(indented) under that folder's name.
*
*   Hint: Use a queue, and see java.io.File.
*
* */
public class Ex43 {
    private Queue<String> fileQueue = new Queue<>();

    //List all files according to their hierarchy
    private void listFiles(File file, int depth){
        if (!file.exists()){return;}
        addFileToPath(file,depth);
        File[] fileList = file.listFiles();
       if (fileList != null){
           for (File fileItem : fileList){
               if (fileItem.isFile()){addFileToPath(fileItem,depth);}
               else if (fileItem.isDirectory()){
                   listFiles(fileItem,depth+1);
               }
           }
       }
    }

    // add all files to their responding position
    private void addFileToPath(File file, int depth){
        StringBuilder stringB = new StringBuilder();
        for (int i = 0; i < depth; i++){
            stringB.append(" ");
        }
        stringB.append(file.getName());
        fileQueue.enqueue(stringB.toString());
    }

    public static void main(String[] args) {
        String folderPath = args[0];
        File folder = new File(folderPath);
        Ex43 listFiles = new Ex43();
        listFiles.listFiles(folder,0);
        for (String filename : listFiles.fileQueue){
            StdOut.println(filename);
        }
    }
}
