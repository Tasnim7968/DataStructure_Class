import java.util.ArrayList;
import java.util.Collections;

public class GenericList<T> {
    private ArrayList<T> items;

    public GenericList() {
        this.items = new ArrayList<>();
    }

    public void add(T item) { items.add(item); }

    public T get(int index) { return items.get(index); }

    public boolean remove(T item) { return items.remove(item); }

    public int size() { return items.size(); }

    public boolean isEmpty() { return items.isEmpty(); }

    public void clear() { items.clear(); }

    public boolean contains(T item) { return items.contains(item); }

    public ArrayList<T> getAll() { return new ArrayList<>(items); }

    public void addAll(ArrayList<T> other) {
        if (other == null) return;
        items.addAll(other);
    }

    public <U extends T> void addAllFrom(GenericList<U> other) {
        if (other == null) return;
        items.addAll(other.getAll());
    }

    public void sort() {
        if (items.isEmpty()) return;
        if (!(items.get(0) instanceof Comparable)) return;
        @SuppressWarnings("unchecked")
        ArrayList<Comparable<Object>> tmp = (ArrayList<Comparable<Object>>) (ArrayList<?>) items;
        Collections.sort(tmp);
    }

    public T findMax() {
        if (items.isEmpty()) return null;
        if (!(items.get(0) instanceof Comparable)) return null;
        @SuppressWarnings("unchecked")
        ArrayList<Comparable<Object>> tmp = (ArrayList<Comparable<Object>>) (ArrayList<?>) items;
        Comparable<Object> max = tmp.get(0);
        for (int i = 1; i < tmp.size(); i++) {
            if (tmp.get(i).compareTo(max) > 0) max = tmp.get(i);
        }
        @SuppressWarnings("unchecked")
        T out = (T) max;
        return out;
    }
}
