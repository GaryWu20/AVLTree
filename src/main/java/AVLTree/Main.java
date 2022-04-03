package AVLTree;

import java.io.*;
import java.util.*;

public class Main {
    
    public static Scanner readFile() throws FileNotFoundException{
        //import file 
        //String bookFile = new File("").getAbsolutePath();
        //bookFile = bookFile.concat("\\books.txt");
  
        File book = new File("src\\main\\java\\AVLTree\\books.txt");
        Scanner books = new Scanner(book);
        return books;
    }
    
    public static void createTree(AVLTreeAssignment tree, Scanner books){
        //run until our file has no lines left to evaluate
        while(books.hasNextLine()){
            //get each line from file
            String data = books.nextLine();
            
            //parse each line to get the respective data fields
            //ISBN = Data[0]
            //Title = Data[1]
            //Author = Data[2]
            String Data[] = data.split(" ");
    
            Book book = new Book(Data[1], Data[2]);
            
            //insert each book after we get the data
            tree.insert(Data[0], book);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner bookFile = readFile();
        AVLTreeAssignment bst = new AVLTreeAssignment();
        createTree(bst, bookFile);
    	bst.prettyPrint();
    }
}
