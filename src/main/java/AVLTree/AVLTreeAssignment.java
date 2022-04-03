package AVLTree;

public class AVLTreeAssignment {

    private Node root;

    public AVLTreeAssignment() {
        root = null;
    }

    private void printHelper(Node currPtr, String indent, boolean last) {
        if (currPtr != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            System.out.println(currPtr.key + "(height = " + currPtr.height + ")");

            printHelper(currPtr.left, indent, false);
            printHelper(currPtr.right, indent, true);
        }
    }

    private Node searchTreeHelper(Node node, String key) {
        if (node == null || Integer.parseInt(key) == Integer.parseInt(node.key)) {
            return node;
        }

        if (Integer.parseInt(key) < Integer.parseInt(node.key)) {
            return searchTreeHelper(node.left, key);
        }
        return searchTreeHelper(node.right, key);
    }

    private Node deleteNodeHelper(Node node, String key) {
        if (node == null) {
            return node;
        } else if (Integer.parseInt(key) < Integer.parseInt(node.key)) {
            node.left = deleteNodeHelper(node.left, key);
        } else if (Integer.parseInt(key) > Integer.parseInt(node.key)) {
            node.right = deleteNodeHelper(node.right, key);
        } else {
            if (node.left == null && node.right == null) {
                node = null;
            } else if (node.left == null) {
                Node temp = node;
                node = node.right;
            } else if (node.right == null) {
                Node temp = node;
                node = node.left;
            } else {
                Node temp = minimum(node.right);
                node.key = temp.key;
                node.right = deleteNodeHelper(node.right, temp.key);
            }

        }

        return node;
    }

    private void updateBalance(Node node, String key) {
        if (node.height < -1 || node.height > 1) {
            rebalance(node, key);
            return;
        }

        if (node.parent != null) {
            if (node == node.parent.left) {
                node.parent.height -= 1;
            }

            if (node == node.parent.right) {
                node.parent.height += 1;
            }

            if (node.parent.height != 0) {
                updateBalance(node.parent, key);
            }
        }
    }

    void rebalance(Node node, String key) {
        System.out.print("Imbalance condition occured at inserting ISBN " + key + "; ");
        if (node.height > 0) {
            if (node.right.height < 0) {
                rightRotate(node.right);
                leftRotate(node);
                System.out.print("fixed ISBN " + node.key + " in RightLeft Rotation \n\n");
            } else {
                leftRotate(node);
                System.out.print("fixed ISBN " + node.key + " in Left Rotation \n\n");
            }
        } else if (node.height < 0) {
            if (node.left.height > 0) {
                leftRotate(node.left);
                rightRotate(node);
                System.out.print("fixed ISBN " + node.key + " in LeftRight Rotation \n\n");
            } else {
                rightRotate(node);
                System.out.print("fixed ISBN " + node.key + " in Right Rotation \n\n");
            }
        }
    }

    private void preOrderHelper(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    private void inOrderHelper(Node node) {
        if (node != null) {
            inOrderHelper(node.left);
            System.out.print(node.key + " ");
            inOrderHelper(node.right);
        }
    }

    private void postOrderHelper(Node node) {
        if (node != null) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            System.out.print(node.key + " ");
        }
    }

    public void preorder() {
        preOrderHelper(this.root);
    }

    public void inorder() {
        inOrderHelper(this.root);
    }

    public void postorder() {
        postOrderHelper(this.root);
    }

    public Node searchTree(String k) {
        return searchTreeHelper(this.root, k);
    }

    public Node minimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public Node maximum(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public Node successor(Node x) {
        if (x.right != null) {
            return minimum(x.right);
        }

        Node y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    public Node predecessor(Node x) {
        if (x.left != null) {
            return maximum(x.left);
        }

        Node y = x.parent;
        while (y != null && x == y.left) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;

        x.height = x.height - 1 - Math.max(0, y.height);
        y.height = y.height - 1 + Math.min(0, x.height);
    }

    void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;

        x.height = x.height + 1 - Math.min(0, y.height);
        y.height = y.height + 1 + Math.max(0, x.height);
    }

    public void insert(String key, Book book) {
        Node node = new Node(key, book);
        Node y = null;
        Node x = this.root;

        while (x != null) {
            y = x;
            if (Integer.parseInt(node.key) < Integer.parseInt(x.key)) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        node.parent = y;
        if (y == null) {
            root = node;
        } else if (Integer.parseInt(node.key) < Integer.parseInt(y.key)) {
            y.left = node;
        } else {
            y.right = node;
        }
        
        updateBalance(node, key);
    }

    Node deleteNode(String data) {
        return deleteNodeHelper(this.root, data);
    }

    public void prettyPrint() {
        printHelper(this.root, "", true);
    }
}
