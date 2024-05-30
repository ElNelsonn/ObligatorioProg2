package ADTs.src.uy.edu.um.prog2.adt.Queue;

import ADTs.src.uy.edu.um.prog2.adt.List.Node;
import ADTs.src.uy.edu.um.prog2.adt.exceptions.EmptyQueueException;


public class MyQueueImpl<T> implements MyQueue<T> {
    private Node<T> firstNode;
    private Node<T> lastNode;
    private int length = 0;

    public MyQueueImpl() {
    }

    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);
        if (this.firstNode == null) {
            this.firstNode = newNode;
        } else {
            this.lastNode.setNextNode(newNode);
        }
        this.lastNode = newNode;
        length++;
    }

    public T dequeue() throws EmptyQueueException {
        if (this.firstNode != null) {
            T value = this.firstNode.getValue();
            this.firstNode = this.firstNode.getNextNode();
            this.length--;
            return value;
        } else {
            throw new EmptyQueueException();
        }
    }

    public boolean contains(T value) {
        if (this.firstNode == null) {
            return false;
        } else {
            Node<T> auxNode = this.firstNode;
            while ((auxNode != null) && !(auxNode.getValue().equals(value))) {
                auxNode = auxNode.getNextNode();
            }
            return (auxNode != null);
        }
    }


    public boolean isEmpty() {
        return (this.firstNode == null);
    }

    public int size(){
        return length;
    }

    public T firstValue() throws EmptyQueueException {
        if (firstNode == null) {
            throw new EmptyQueueException();
        }
        return this.firstNode.getValue();
    }

    public Node<T> getFirst() {
        return firstNode;
    }

    public void setFirst(Node<T> first) {
        this.firstNode = first;
    }

    public Node<T> getLast() {
        return lastNode;
    }

    public void setLast(Node<T> last) {
        this.lastNode = last;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
