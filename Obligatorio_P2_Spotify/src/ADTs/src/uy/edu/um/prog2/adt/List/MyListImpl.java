package ADTs.src.uy.edu.um.prog2.adt.List;
import ADTs.src.uy.edu.um.prog2.adt.exceptions.*;

public class MyListImpl<T> implements MyList<T> {
    private Node<T> firstNode;
    private int size = 0;


    public MyListImpl() {
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (this.firstNode == null) {
            this.firstNode = newNode;
        } else {
            Node<T> auxNode = firstNode;
            while (auxNode.getNextNode() != null){
                auxNode = auxNode.getNextNode();
            }
            auxNode.setNextNode(newNode);
        }
        this.size++;
    }

    public T get(int position) throws EmptyListException, OutOfRangeException {
        if (firstNode == null) {
            throw new EmptyListException();
        } else {
            Node<T> auxNode = firstNode;
            if (position < size) {
                int iter = 0;
                while (iter < position) {
                    auxNode = auxNode.getNextNode();
                    iter++;
                }
                return auxNode.getValue();
            } else {
                throw new OutOfRangeException();
            }
        }
    }

    public boolean contains(T value){
        Node<T> auxNode = firstNode;
        while ((auxNode != null) && (!auxNode.getValue().equals(value))) {
            auxNode = auxNode.getNextNode();
        }
        return (auxNode != null);
    }

    public void remove(T value) throws ElementNotFound, EmptyListException{
        if (size == 0) {
            throw new EmptyListException();
        } else if (!contains(value)) {
            throw new ElementNotFound();
        } else {
            if (firstNode.getValue().equals(value)) {
                Node<T> auxNode = firstNode;
                firstNode = firstNode.getNextNode();
                auxNode.setNextNode(null);

            } else {
                Node<T> auxNode1 = firstNode;
                Node<T> auxNode2 = firstNode.getNextNode();
                while (!auxNode2.getValue().equals(value)) {
                    auxNode1 = auxNode2;
                    auxNode2 = auxNode2.getNextNode();
                }
                auxNode1.setNextNode(auxNode2.getNextNode());
                auxNode2.setNextNode(null);
            }
            size--;
        }
    }

    public int size() {
        return size;
    }

    public Node<T> getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(Node<T> firstNode) {
        this.firstNode = firstNode;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
