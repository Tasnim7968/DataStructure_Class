import java.util.ArrayList;

public class GenericQueue<T> {
    private ArrayList<T> items = new ArrayList<>();

    public void enqueue(T item) { items.add(item); }

    public T dequeue() {
        if (items.isEmpty()) return null;
        return items.remove(0);
    }

    public T peek() {
        if (items.isEmpty()) return null;
        return items.get(0);
    }

    public boolean isEmpty() { return items.isEmpty(); }

    public int size() { return items.size(); }
}
