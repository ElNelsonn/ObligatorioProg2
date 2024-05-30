package ADTs.src.uy.edu.um.prog2.adt.List;

import java.util.Objects;

public class Node<T> {
    private T value;
    private Node<T> nextNode;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Node<T>  getNextNode(){
        return  this.nextNode;
    }

    public void setNextNode(Node<T> node){
        this.nextNode = node;
    }
    public void setValue(T value){
        this.value = value;
    }
}
