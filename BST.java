/**
 * Creates a BST that has the methods find, delete, print, insert
 * @param <T> the template
 * @author Justine Huynh
 */
public class BST<T>{

    // only node we need is root
    private BSTNode root;

    // every data in node is comparable. Type cast root.data into (T)
    /**
     * Constructs an Object of type BSTNode. Each root node has data, a left node, and a right node
     */
    public class BSTNode {
        private Comparable data;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(Comparable value) {
            this.data = value;
        }

        public Comparable getData(){
            return data;
        }

        public BSTNode getLeft() {
            return left;
        }

        public BSTNode getRight() {
            return right;
        }
        
    }

    /**
     * Deletes a given value from the BST
     * @param value
     */
    public void delete(Comparable value) {
        root = delete(root, value);
    }

    /**
     * Deletes a given value from the BST; private version of delete
     * @param node the node that has the value
     * @param value the value to delete
     * @return the deleted node
     */
    private BSTNode delete(BSTNode node, Comparable value) {
        if (node == null) {
            return null;
        }
        if (node.data.compareTo(value) == 0) { // match
            if (node.left == null) {
                return node.right;
            }
            else if (node.right == null) {
                return node.left;
            }
            else if (node.right.left == null){
                    // if leftmost node on RHS is null
                    node.data = node.right.data; // adopt node's right data
                    node.right = node.right.right; // node's right child adopts the next right child
                    return node;
            }
            else {
                node.data = removeSmallest(node.right); // find IOS
                return node;
            }
        }
        else if (node.data.compareTo(value) > 0) { // if node is too small, go right
            node.right = delete(node.left, value);
        }
        else {
                delete(node.right, value);
        }
        return node;

    }

    /**
     * Finds the smallest child on LHS of root
     * @param node the root node
     * @return the smallest node
     */
    private Comparable removeSmallest(BSTNode node) {
        if (node.left.left == null) {
            Comparable smallest = node.left.data;
            node.left = node.left.right;
            return smallest;
        }
        else {
            return removeSmallest(node.left); // keep going down to search for smallets
        }
    }

    public boolean find(Comparable value){
      return  find(root, value);
    }
    /**
     * Finds a given value in the BST
     * @param root the node to start looking
     * @param value the value to look for
     */
    public boolean find(BSTNode root, Comparable value) {
        if (root == null) {
            return false;
        }
        if (root.data.compareTo(value) == 0) {
            return true;
        }
        else if (root.data.compareTo(value)  < 0) {
            return find(root.right, value);
        }
        return find(root.left, value);
    }

    /**
     * Inserts a given value into the BST
     * @param value the value to be compared to
     */
    public void insert(Comparable value) {
        root = insert(root, value);
    }

    /**
     * Private version of BST insert
     * @param node the root node
     * @param value the value to be compared to
     * @return the node
     */
    private BSTNode insert(BSTNode node, Comparable value) {
        if (node == null) {
            BSTNode newNode = new BSTNode(value);
            return newNode;
        }
        else if ( ( node.data).compareTo(value) < 0) {
            node.right = insert(node.right, value);
        }
        else if((node.data).compareTo(value) > 0){
            node.left = insert(node.left, value);
        }

        return node;
    }


    /**
     * Prints all the nodes in order
     */
    public void print() {
            print(root); // go left recursively until you hit null
    }

    /**
     * Private version of print; prints all the nodes in order
     * @param node the node that we compare other nodes to
     */
    private void print(BSTNode node) {
        if (node != null) {
            print(node.left);
            System.out.println(node.data);
            print(node.right);
        }
    }

}

