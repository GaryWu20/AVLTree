package AVLTree;

public class Node {
    String key; 
    Book book;
    Node parent; 
    Node left; 
    Node right; 
    int height; 

    public Node(String key, Book book) {
        this.key = key;
        this.book = book;
	this.parent = null;
	this.left = null;
	this.right = null;
	this.height = 0;
    }
}
