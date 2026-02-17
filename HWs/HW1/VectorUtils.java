import java.util.Vector;

public class VectorUtils {

    public interface Predicate<T> {
        boolean test(T value);
    }

    public static <T> void swap(Vector<T> vec, int index1, int index2) {
        if (vec == null) throw new IllegalArgumentException("Vector cannot be null");
        if (index1 < 0 || index2 < 0 || index1 >= vec.size() || index2 >= vec.size()) {
            throw new IndexOutOfBoundsException("Invalid indices for swap.");
        }
        if (index1 == index2) return;
        T tmp = vec.get(index1);
        vec.set(index1, vec.get(index2));
        vec.set(index2, tmp);
    }

    public static <T extends Comparable<T>> T findMax(Vector<T> vec) {
        if (vec == null || vec.isEmpty()) return null;
        T max = vec.get(0);
        for (int i = 1; i < vec.size(); i++) {
            T cur = vec.get(i);
            if (cur != null && (max == null || cur.compareTo(max) > 0)) {
                max = cur;
            }
        }
        return max;
    }

    public static <T> int countMatches(Vector<T> vec, T target) {
        if (vec == null) return 0;
        int count = 0;
        for (int i = 0; i < vec.size(); i++) {
            T cur = vec.get(i);
            if (target == null) {
                if (cur == null) count++;
            } else {
                if (target.equals(cur)) count++;
            }
        }
        return count;
    }

    public static <T> Vector<T> filter(Vector<T> vec, Predicate<T> condition) {
        Vector<T> result = new Vector<>();
        if (vec == null || condition == null) return result;

        for (int i = 0; i < vec.size(); i++) {
            T item = vec.get(i);
            if (condition.test(item)) result.add(item);
        }
        return result;
    }

    // Part 4.3: Bounded generics for Number
    public static <T extends Number> double sumNumbers(Vector<T> numbers) {
        if (numbers == null || numbers.isEmpty()) return 0.0;
        double sum = 0.0;
        for (int i = 0; i < numbers.size(); i++) {
            T n = numbers.get(i);
            if (n != null) sum += n.doubleValue();
        }
        return sum;
    }

    public static <T extends Number> double averageNumbers(Vector<T> numbers) {
        if (numbers == null || numbers.isEmpty()) return 0.0;
        double sum = sumNumbers(numbers);
        return sum / numbers.size();
    }
}
