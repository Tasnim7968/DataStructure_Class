import java.util.ArrayList;
import java.util.Collections;

public class ArrayListUtils {

    public static <T> void swap(ArrayList<T> list, int index1, int index2) {
        if (list == null) return;
        if (index1 < 0 || index2 < 0 || index1 >= list.size() || index2 >= list.size()) return;
        T tmp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, tmp);
    }

    public static <T extends Comparable<T>> T findMax(ArrayList<T> list) {
        if (list == null || list.isEmpty()) return null;
        T max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            T cur = list.get(i);
            if (cur.compareTo(max) > 0) max = cur;
        }
        return max;
    }

    public interface Predicate<T> {
        boolean test(T item);
    }

    public static <T> ArrayList<T> filter(ArrayList<T> list, Predicate<T> condition) {
        ArrayList<T> out = new ArrayList<>();
        if (list == null || condition == null) return out;
        for (T item : list) {
            if (condition.test(item)) out.add(item);
        }
        return out;
    }

    public static <T> void reverse(ArrayList<T> list) {
        if (list == null) return;
        int i = 0, j = list.size() - 1;
        while (i < j) {
            swap(list, i, j);
            i++;
            j--;
        }
    }

    public static <T> ArrayList<T> merge(ArrayList<T> list1, ArrayList<T> list2) {
        ArrayList<T> out = new ArrayList<>();
        if (list1 != null) out.addAll(list1);
        if (list2 != null) out.addAll(list2);
        return out;
    }

    public static <T extends Number> double sum(ArrayList<T> numbers) {
        if (numbers == null || numbers.isEmpty()) return 0.0;
        double s = 0.0;
        for (T n : numbers) s += n.doubleValue();
        return s;
    }

    public static <T extends Number> double average(ArrayList<T> numbers) {
        if (numbers == null || numbers.isEmpty()) return 0.0;
        return sum(numbers) / numbers.size();
    }

    public static <T extends Number & Comparable<T>> ArrayList<T> filterAbove(ArrayList<T> numbers, T threshold) {
        ArrayList<T> out = new ArrayList<>();
        if (numbers == null || threshold == null) return out;
        for (T n : numbers) {
            if (n.compareTo(threshold) > 0) out.add(n);
        }
        return out;
    }

    public static double sumNumbers(ArrayList<? extends Number> numbers) {
        if (numbers == null || numbers.isEmpty()) return 0.0;
        double s = 0.0;
        for (Number n : numbers) s += n.doubleValue();
        return s;
    }

    public static void addNumbers(ArrayList<? super Integer> list) {
        if (list == null) return;
        list.add(1);
        list.add(2);
        list.add(3);
    }

    public static void printList(ArrayList<?> list) {
        if (list == null) {
            System.out.println("(null)");
            return;
        }
        for (Object o : list) System.out.println(o);
    }

    public static <T extends Comparable<T>> void sort(ArrayList<T> list) {
        if (list == null) return;
        Collections.sort(list);
    }
}
