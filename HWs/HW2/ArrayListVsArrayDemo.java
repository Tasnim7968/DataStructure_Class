import java.util.ArrayList;
import java.util.Random;

public class ArrayListVsArrayDemo {

    public static void runDemo() {
        Student[] array = new Student[3];
        array[0] = new Student("S100", "A", "A", "a@u.edu", 3.0, "CS", 1);
        array[1] = new Student("S101", "B", "B", "b@u.edu", 3.1, "CS", 1);
        array[2] = new Student("S102", "C", "C", "c@u.edu", 3.2, "CS", 1);

        ArrayList<Student> list = new ArrayList<>();
        list.add(array[0]);
        list.add(array[1]);
        list.add(array[2]);

        long t1 = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            list.add(new Student("S" + (20000 + i), "F", "L", "x@u.edu", 3.0, "CS", 2));
        }
        long t2 = System.nanoTime();

        Random r = new Random(42);
        long t3 = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            int idx = r.nextInt(list.size());
            list.get(idx);
        }
        long t4 = System.nanoTime();

        System.out.println("PERFORMANCE REPORT");
        System.out.println("Array: fixed size, adding requires new array + copy (limitation).");
        System.out.println("ArrayList: dynamic size, add/remove supported easily.");
        System.out.println("Time add 10,000 students (ArrayList): " + ((t2 - t1) / 1_000_000.0) + " ms");
        System.out.println("Time access 1,000 random students (ArrayList): " + ((t4 - t3) / 1_000_000.0) + " ms");
    }
}
