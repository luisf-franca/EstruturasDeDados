public class UniBHList<T extends Comparable<T>> {
    // Hold the reference to the first node of this List.
    private Node<T> firstNode;
    private int totalElements;

    public void insertAtBeginning(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.setNext(firstNode);
        firstNode = newNode;
        totalElements++;
    }

    public Node<T> removeAtBeginning() {
        Node<T> aux = firstNode;
        firstNode = firstNode.getNext();
        totalElements--;
        return aux;
    }

    public void insertAtEnd(T value) {
        Node<T> newNode = new Node<>(value);
        if (firstNode == null) {
            firstNode = newNode;
        } else {
            Node<T> currentNode = firstNode;
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
        }
        totalElements++;
    }

    public void insertInOrder(T value) {
        Node<T> newNode = new Node<>(value);
        if (firstNode == null || firstNode.getValue().compareTo(value) > 0) {
            newNode.setNext(firstNode);
            firstNode = newNode;
        } else {
            Node<T> currentNode = firstNode;
            while (currentNode.getNext() != null && currentNode.getNext().getValue().compareTo(value) < 0) {
                currentNode = currentNode.getNext();
            }
            newNode.setNext(currentNode.getNext());
            currentNode.setNext(newNode);
        }
        totalElements++;
    }

    public Node<T> removeAtEnd() {
        if (firstNode == null) {
            return null;
        }
        if (firstNode.getNext() == null) {
            Node<T> aux = firstNode;
            firstNode = null;
            totalElements--;
            return aux;
        }
        Node<T> currentNode = firstNode;
        while (currentNode.getNext().getNext() != null) {
            currentNode = currentNode.getNext();
        }
        Node<T> aux = currentNode.getNext();
        currentNode.setNext(null);
        totalElements--;
        return aux;
    }

    public boolean removeByValue(T value) {
        if (firstNode == null) {
            return false;
        }
        if (firstNode.getValue().equals(value)) {
            firstNode = firstNode.getNext();
            totalElements--;
            return true;
        }
        Node<T> currentNode = firstNode;
        while (currentNode.getNext() != null && !currentNode.getNext().getValue().equals(value)) {
            currentNode = currentNode.getNext();
        }
        if (currentNode.getNext() == null) {
            return false;
        }
        currentNode.setNext(currentNode.getNext().getNext());
        totalElements--;
        return true;
    }

    public boolean search(T value) {
        Node<T> currentNode = firstNode;
        while (currentNode != null) {
            if (currentNode.getValue().equals(value)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.totalElements == 0) {
            return "[ ]";
        }

        Node<T> currentNode = firstNode;
        StringBuilder builder = new StringBuilder("[");

        for (int i = 0; i < totalElements; i++) {
            builder.append(currentNode.getValue());
            builder.append(", ");

            currentNode = currentNode.getNext();

        }

        builder.append("]");

        return builder.toString();
    }

    // Design the other list methods.
    // Insert at the end, in order, remove at the end,
    // remove elements by value, search an element.
}