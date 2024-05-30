package ADTs.src.uy.edu.um.prog2.adt.Tree;

import ADTs.src.uy.edu.um.prog2.adt.exceptions.ElementNotFound;
import ADTs.src.uy.edu.um.prog2.adt.exceptions.EmptyTreeException;

public class MyTreeImpl<K extends Comparable<K>, T> implements MyTree<K, T> {
    private TreeNode<K, T> root;

    public MyTreeImpl() {
        this.root = null;
    }

    public TreeNode<K, T> getRoot() {
        return this.root;
    }


    //--Prints--
    private void printPreOrder2(TreeNode root){
        if (root != null){
            System.out.println(root.getValue());
            printPreOrder2(root.getLeft());
            printPreOrder2(root.getRight());
        }
    }
    public void printPreOrder(){
        printPreOrder2(this.root);
    }

    public void printInOrder(){
        printInOrder2(this.root);
    }
    private void printInOrder2(TreeNode root) {
        if (root != null) {
            printInOrder2(root.getLeft());
            System.out.println(root.getValue());
            printInOrder2(root.getRight());
        }
    }

    public void printPostOrder(){
        printPostOrder2(this.root);
    }
    private void printPostOrder2(TreeNode root){
        if (root != null){
            printInOrder2(root.getLeft());
            printInOrder2(root.getRight());
            System.out.println(root.getValue());
        }
    }

    //--Find--
    public T findSort(K key) {
        return (findSort2(key, this.root));
    }
    private T findSort2(K key, TreeNode<K, T> root) {
        if (root == null) {
            return null;
        } else if (root.getKey().equals(key)) {
            return root.getValue();
        } else {
            //Comparas key con root
            if (root.getKey().compareTo(key) < 0) {
                return findSort2(key, root.getRight());
            } else {
                return findSort2(key, root.getLeft());
            }

        }
    }

    //--Size--
    public int size(){
        return size2(this.root);
    }
    private int size2(TreeNode root){
        if(root == null){
            return 0;
        } else {
            int tamder = size2(root.getRight());
            int tamdech = size2(root.getLeft());
            return 1+tamder+tamdech;
        }
    }


    //--Auxiliar--
    private TreeNode<K,T> mayor(TreeNode<K,T> root){
        if (root == null){
            return null;
        } else {
            while (root.getRight()!=null){
                root = root.getRight();
            }
            return root;
        }
    }

    private TreeNode<K,T> menor(TreeNode<K,T> root){
        if (root == null){
            return null;
        } else {
            TreeNode<K,T> aux = root;
            while (aux.getLeft()!=null){
                aux = aux.getLeft();
            }
            return aux;
        }
    }




    //--CountLeaf--
    public int countLeaf(){
        return countLeaf2(this.root);
    }
    private int countLeaf2(TreeNode root){
        if(root == null) {
            return 0;
        }else if (root.getLeft()==null && root.getRight()==null){
            return 1;
        } else {
            int der = countLeaf2(root.getRight());
            int izq = countLeaf2(root.getLeft());
            return izq+der;
        }
    }


    //--CountFullParents--
    public int countCompleteElements(){
        return countCompleteElements2(this.root);
    }
    private int countCompleteElements2(TreeNode root){
        if (root==null){
            return 0;
        } else if ((root.getLeft()!=null)&&(root.getRight()!=null)){
            return 1+countCompleteElements2(root.getRight())+countCompleteElements2(root.getLeft());
        } else {
            int der = countCompleteElements2(root.getRight());
            int izq = countCompleteElements2(root.getLeft());
            return izq+der;
        }
    }


    //--Insert--
    public void insert(T value, K key) {
        this.root = insert2(value, key, this.root);
    }
    private TreeNode insert2(T value, K key, TreeNode<K, T> root) {
        if (root == null) {
            //System.out.println("Cree primer nodo del arbol");
            TreeNode<K, T> newNode = new TreeNode<>(key, value);
            root = newNode;
            return root;
        } else if (key.compareTo(root.getKey()) < 0) {
            TreeNode<K, T> left = insert2(value, key, root.getLeft());
            root.setLeft(left);
        } else if (key.compareTo(root.getKey()) > 0) {
            TreeNode<K, T> right = insert2(value, key, root.getRight());
            root.setRight(right);
        }
        return root;
    }


    //--Delete--
    public void delete(K key) throws ElementNotFound,EmptyTreeException {
        if (this.root == null){
            throw new EmptyTreeException();
        } else {
            T elem = findSort(key);
            if (elem!=null){
                TreeNode<K, T> aux = delete2(key, this.root);
                this.root = aux;
            } else {
                throw  new ElementNotFound();
            }
        }
    }

    private TreeNode<K, T> delete2(K key,TreeNode<K, T> root) {
        if(root==null) {
            return null;
        } else if (key.compareTo(root.getKey()) < 0) {
            root.setLeft(delete2(key,root.getLeft()));
        } else if (key.compareTo(root.getKey()) > 0){
            root.setRight(delete2(key,root.getRight()));
        } else if((root.getLeft()==null)&&(root.getRight()==null)){
            return null;
        } else if ((root.getRight() == null) && (root.getLeft() != null)){
            return root.getLeft();
        } else if ((root.getLeft() == null) && (root.getRight() != null)){
            return root.getRight();
        } else {
            TreeNode<K,T> menor = menor(root.getRight());
            root.setKey(menor.getKey());
            root.setValue(menor.getValue());
            root.setRight(delete2(menor.getKey(),root.getRight()));
            return root;
        }
        return root;
    }
}
