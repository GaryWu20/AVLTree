package AVLTree;

public class Book {
    private String title;
    private String author;

    Book(String title, String author){
        this.title = title;
        this.author = author;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setAuthor(String title){
        this.author = author;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public String getAuthor(){
        return this.author;
    }
}
