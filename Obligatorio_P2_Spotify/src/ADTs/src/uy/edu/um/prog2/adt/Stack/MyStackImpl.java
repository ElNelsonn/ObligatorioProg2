package ADTs.src.uy.edu.um.prog2.adt.Stack;

import ADTs.src.uy.edu.um.prog2.adt.List.Node;
import ADTs.src.uy.edu.um.prog2.adt.exceptions.EmptyStackException;

public class MyStackImpl<T> implements MyStack<T> {
    private Node<T> first;
    private int length = 0;

    public MyStackImpl() {
    }

    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        if (this.first != null) {
            newNode.setNextNode(this.first);
        } 
        this.first = newNode;
        this.length++;
    }

    public T peek() throws EmptyStackException {
        if (this.first == null) {
            throw new EmptyStackException();
        }
        return this.first.getValue();
    }

    public int size(){
        return this.length;
    }

    public T pop() throws EmptyStackException {
        if (this.first == null) {
            throw new EmptyStackException();
        } else {
            T value = this.first.getValue();
            this.first = this.first.getNextNode();
            this.length--;
            return value;
        }
    }

    public boolean contains(T value) {
        if (this.first == null) {
            return false;
        } else {
            Node<T> auxNode = this.first;
            while ((auxNode != null) && !(auxNode.getValue().equals(value))) {
                auxNode = auxNode.getNextNode();
            }
            return (auxNode != null);
        }
    }




    public Node<T> getFirst() {
        return first;
    }

    public void setFirst(Node<T> first) {
        this.first = first;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
