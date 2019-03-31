/**
 * Creates a BST that has the methods find, delete, print, insert
 * @param <T> the template
 * @author Justine Huynh
 */
public class BST<T> implements Comparable<T> {

    // only node we need is root
    private BSTNode<T> root;

    /**
     * Constructs an Object of type BSTNode. Each root node has data, a left node, and a right node
     * @param <T> the template
     */
    public class BSTNode<T> {
        private Comparable<T> data;
        private BSTNode<T> left;
        private BSTNode<T> right;

        public BSTNode(Comparable value) {
            this.data = value;
        }


    }

    /**
     * Compares a given value with the current node's value
     * @param value the given value
     * @return 0 if node's value and given value are equal; 1 if node's value is greater than
     * given value; -1 if node's value is less than given value
     */
    public int data_compareTo(Comparable value) {
        if (root.data == value) {
            return 0;
        }
        else if (root.data > value) {
            return 1;
        }
        return -1;
    }


    /**
     * Deletes a given value from the BST
     * @param value
     */
    public void delete(Comparable value) {
        delete(root, value);
    }

    private BSTNode<T> delete(BSTNode<T> node, Comparable value) {
        if (node == null) {
            return null;
        }
        if (data_compareTo(value) == 0) { // match
            if (node.left == null) {
                return node.right;
            }
            else if (node.right == null) {
                return node.left;
            }
            else { // if both left and right child exists
                if (node.right.left == null){
                    // if leftmost node on RHS is null
                    node.data = node.right.data; // adopt node's right data
                    node.right = node.right.right; // node's right child adopts the next right child
                }
                else {
                    node.data = removeSmallest(node.right); // find IOS
                }
            }
        }
        else if (data_compareTo(value) < 0) { // if node is too small, go right
            node.right = delete(node.right, value);
            return node.right;
        }
//        else {
        node.left = delete(node.left, value);
        return node.left;
//        }

    }

    /**
     * Finds the smallest child on LHS of root
     * @param node the root node
     * @return the smallest node
     */
    private Comparable removeSmallest(BSTNode<T> node) {
        if (node.left.left == null) {
            Comparable smallest = node.left.data;
            node.left = node.left.right;
            return smallest;
        }
        else {
            return removeSmallest(node.left); // keep going down to search for smallets
        }
    }


    /**
     * Finds a given value in the BST
     * @param value
     */
    public boolean find(BSTNode<T> root, BSTNode<T> tempNode, Comparable value) {
        if (root == null) {
            return false;
        }
        if (data_compareTo (value) == 0) {
            return true;
        }
        else if (data_compareTo(value) > 0) {
            return find(root, root.right, value);
        }
        return find(root, root.left, value);
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
    private BSTNode<T> insert(BSTNode node, Comparable  value) {
        if (node == null) {
            BSTNode<T> newNode = new BSTNode<T>(value);
            return newNode;
        }
        else if (data_compareTo(value) > 0) {
            node.right = insert(node.right, value);
        }
        else {
            node.left = insert(node.left, value);
        }
        return node;
    }


    /**
     * Prints all the nodes in order
     */
    public void print() {
        if (root != null) {
            print(root.left); // go left recursively until you hit null
        }

    }

    /**
     * Private version of print; prints all the nodes in order
     * @param node the node that we compare other nodes to
     */
    private void print(BSTNode<T> node) {
        if (root != null) {
            print(root.left);
            System.out.println(node.data);
            print(node.right);
        }
    }

}

