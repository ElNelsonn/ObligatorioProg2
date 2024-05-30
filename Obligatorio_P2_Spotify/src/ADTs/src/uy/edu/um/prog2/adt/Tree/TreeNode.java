package ADTs.src.uy.edu.um.prog2.adt.Tree;

public class TreeNode<K, T> {
    private K key;
    private T value;
    private TreeNode<K,T> right;
    private TreeNode<K,T> left;

    public TreeNode(K key, T value) {
        this.key = key;
        this.value = value;
        this.right = null;
        this.left = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TreeNode<K, T> getRight() {
        return right;
    }

    public void setRight(TreeNode<K, T> right) {
        this.right = right;
    }

    public TreeNode<K, T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<K, T> left) {
        this.left = left;
    }
}
