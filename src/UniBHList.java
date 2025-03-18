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

    public boolean removeByValue(T value) throws Exception {
        if (firstNode == null) {
            throw new Exception("Value not present");
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
            throw new Exception("Value not present");
        }
        currentNode.setNext(currentNode.getNext().getNext());
        totalElements--;
        return true;
    }

    public boolean search(T value) throws Exception {
        Node<T> currentNode = firstNode;
        while (currentNode != null) {
            if (currentNode.getValue().equals(value)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        throw new Exception("Item not found");
    }

    public boolean isEmpty() {
        return totalElements == 0;
    }

    public void insertAfter(int index, T value) throws Exception {
        if (index < 0 || index >= totalElements) {
            throw new Exception("Index out of bounds");
        }
        Node<T> newNode = new Node<>(value);
        Node<T> currentNode = firstNode;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        newNode.setNext(currentNode.getNext());
        currentNode.setNext(newNode);
        totalElements++;
    }

    public Node<T> removeAt(int index) throws Exception {
        if (index < 0 || index >= totalElements) {
            throw new Exception("Index out of bounds");
        }
        if (index == 0) {
            return removeAtBeginning();
        }
        Node<T> currentNode = firstNode;
        for (int i = 0; i < index - 1; i++) {
            currentNode = currentNode.getNext();
        }
        Node<T> aux = currentNode.getNext();
        currentNode.setNext(currentNode.getNext().getNext());
        totalElements--;
        return aux;
    }

    public int size() {
        return totalElements;
    }

    public void modifyElement(T oldValue, T newValue) throws Exception {
        Node<T> currentNode = firstNode;
        while (currentNode != null) {
            if (currentNode.getValue().equals(oldValue)) {
                currentNode.setValue(newValue);
                return;
            }
            currentNode = currentNode.getNext();
        }
        throw new Exception("Element not found");
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

class Node<T> {
    private T value;
    private Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}