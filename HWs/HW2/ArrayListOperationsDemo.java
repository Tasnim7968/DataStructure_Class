import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ArrayListOperationsDemo {

    public static void runDemo() {
        Student[] arr = new Student[] {
                new Student("S010", "Zoe", "Adams", "zoe@u.edu", 3.2, "Math", 2),
                new Student("S011", "Ian", "Brown", "ian@u.edu", 3.9, "CS", 3),
                new Student("S012", "Mia", "Clark", "mia@u.edu", 2.8, "CS", 1),
        };

        ArrayList<Student> listFixed = new ArrayList<>(Arrays.asList(arr));
        listFixed.add(new Student("S013", "Liam", "Davis", "liam@u.edu", 3.5, "Math", 4));
        listFixed.remove(0);

        Student[] backToArray = listFixed.toArray(new Student[0]);
        System.out.println("ArrayList -> Array length: " + backToArray.length);

        ArrayList<Student> students = new ArrayList<>(listFixed);
        students.add(new Student("S014", "Ava", "Evans", "ava@u.edu", 3.7, "CS", 2));
        students.add(new Student("S015", "Noah", "Fox", "noah@u.edu", 3.1, "Math", 1));

        System.out.println("SubList demo:");
        ArrayList<Student> base = new ArrayList<>(students);
        java.util.List<Student> sub = base.subList(1, Math.min(3, base.size()));
        if (!sub.isEmpty()) sub.remove(0);
        System.out.println("Original size after subList modify: " + base.size());

        // Sorting: Comparator for GPA DESC
        Collections.sort(students, Comparator.comparingDouble(Student::getGpa).reversed());
        // Sorting: Comparator for lastName A->Z
        Collections.sort(students, Comparator.comparing(Student::getLastName, String.CASE_INSENSITIVE_ORDER));

        Student target = students.get(0);
        System.out.println("indexOf target: " + students.indexOf(target));
        System.out.println("contains target: " + students.contains(target));

        Collections.sort(students);
        int idx = Collections.binarySearch(students, target);
        System.out.println("binarySearch (after sort): " + idx);
    }
}
