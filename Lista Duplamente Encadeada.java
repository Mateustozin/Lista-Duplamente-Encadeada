import java.util.Objects;

// Definição do nó
class Node<T> {
    T value;
    Node<T> prev;
    Node<T> next;

    Node(T value) {
        this.value = value;
    }
}


public class ListaDuplementeEncadeada<T> {
    private Node<T> base;
    private Node<T> top;
    private int size;

   
    public boolean isEmpty() {
        return size == 0;
    }

   
    public boolean isFull() {
        return false; 
    }

   
    public int getSize() {
        return size;
    }

    
    public void add(int pos, T value) {
        Objects.checkIndex(pos, size); 

        Node<T> newNode = new Node<>(value);
        if (pos == 0) { 
            if (isEmpty()) {
                base = top = newNode;
            } else {
                newNode.next = base;
                base.prev = newNode;
                base = newNode;
            }
        } else if (pos == size) { 
            top.next = newNode;
            newNode.prev = top;
            top = newNode;
        } else { 
            Node<T> current = getNode(pos);
            newNode.next = current;
            newNode.prev = current.prev;
            if (current.prev != null) {
                current.prev.next = newNode;
            }
            current.prev = newNode;
        }
        size++;
    }

    
    public void add(T value) {
        add(size, value);
    }

   
    public T remove(int pos) {
        Objects.checkIndex(pos, size);
        Node<T> toRemove = getNode(pos);

        if (toRemove == base) {
            base = toRemove.next;
            if (base != null) {
                base.prev = null;
            }
        } else if (toRemove == top) {
            top = toRemove.prev;
            if (top != null) {
                top.next = null;
            }
        } else {
            toRemove.prev.next = toRemove.next;
            toRemove.next.prev = toRemove.prev;
        }

        size--;
        return toRemove.value;
    }

    
    public T remove(Node<T> node) {
        if (node == null) {
            return null;
        }

        if (node == base) {
            base = node.next;
            if (base != null) {
                base.prev = null;
            }
        } else if (node == top) {
            top = node.prev;
            if (top != null) {
                top.next = null;
            }
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        size--;
        return node.value;
    }

   
    public Node<T> getNode(int pos) {
        Objects.checkIndex(pos, size); 
        Node<T> current;

        if (pos < size / 2) {
            current = base;
            for (int i = 0; i < pos; i++) {
                current = current.next;
            }
        } else {
            current = top;
            for (int i = size - 1; i > pos; i--) {
                current = current.prev;
            }
        }

        return current;
    }

    
    public T get(int pos) {
        return getNode(pos).value;
    }

    
    public void set(int pos, T value) {
        Node<T> node = getNode(pos);
        if (node != null) {
            node.value = value;
        }
    }

   
    public int find(T value) {
        Node<T> current = base;
        int index = 0;

        while (current != null) {
            if (Objects.equals(current.value, value)) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1; 
    }
}