import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystemMain {

    public static void main(String[] args) {
        StudentManager sm = new StudentManager();
        CourseManager cm = new CourseManager();
        EnrollmentManager em = new EnrollmentManager(cm);
        ReportGenerator rg = new ReportGenerator();

        seedData(sm, cm, em);

        Scanner sc = new Scanner(System.in);
        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();

            if (choice.equals("1")) {
                System.out.print("StudentId: ");
                String id = sc.nextLine().trim();
                System.out.print("First: ");
                String fn = sc.nextLine().trim();
                System.out.print("Last: ");
                String ln = sc.nextLine().trim();
                System.out.print("Email: ");
                String email = sc.nextLine().trim();
                System.out.print("GPA: ");
                double gpa = parseDouble(sc.nextLine().trim());
                System.out.print("Major: ");
                String major = sc.nextLine().trim();
                System.out.print("Year (1-4): ");
                int year = parseInt(sc.nextLine().trim());
                sm.addStudent(new Student(id, fn, ln, email, gpa, major, year));
            } else if (choice.equals("2")) {
                System.out.print("StudentId to remove: ");
                System.out.println(sm.removeStudent(sc.nextLine().trim()) ? "Removed." : "Not found.");
            } else if (choice.equals("3")) {
                System.out.print("StudentId to find: ");
                Student s = sm.findStudent(sc.nextLine().trim());
                System.out.println(s == null ? "Not found." : s.toString());
            } else if (choice.equals("4")) {
                sm.printAllStudents();
            } else if (choice.equals("5")) {
                System.out.print("CourseCode: ");
                String code = sc.nextLine().trim();
                System.out.print("CourseName: ");
                String name = sc.nextLine().trim();
                System.out.print("Credits: ");
                int cr = parseInt(sc.nextLine().trim());
                System.out.print("Instructor: ");
                String inst = sc.nextLine().trim();
                System.out.print("Max enrollment: ");
                int max = parseInt(sc.nextLine().trim());
                cm.addCourse(new Course(code, name, cr, inst, max));
            } else if (choice.equals("6")) {
                System.out.print("StudentId: ");
                String sid = sc.nextLine().trim();
                System.out.print("CourseCode: ");
                String cc = sc.nextLine().trim();
                System.out.print("Semester (e.g., Fall 2024): ");
                String sem = sc.nextLine().trim();
                em.enrollStudent(sid, cc, sem);
            } else if (choice.equals("7")) {
                System.out.print("EnrollmentId (e.g., E001): ");
                String eid = sc.nextLine().trim();
                System.out.print("Grade (A/B/C/D/F): ");
                String g = sc.nextLine().trim();
                em.assignGrade(eid, g);
            } else if (choice.equals("8")) {
                System.out.print("StudentId: ");
                String sid = sc.nextLine().trim();
                System.out.println("Student GPA (from enrollments): " + String.format("%.2f", em.calculateStudentGpa(sid)));
            } else if (choice.equals("9")) {
                runReports(sc, sm, cm, em, rg);
            } else if (choice.equals("10")) {
                System.out.println("Bye.");
                break;
            } else if (choice.equalsIgnoreCase("demo")) {
                ArrayListOperationsDemo.runDemo();
                ArrayListVsArrayDemo.runDemo();
                runGenericShowcase(sm);
            } else {
                System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }

    private static void runReports(Scanner sc, StudentManager sm, CourseManager cm, EnrollmentManager em, ReportGenerator rg) {
        System.out.println("Reports:");
        System.out.println("1) Student report");
        System.out.println("2) Course report");
        System.out.println("3) Major report");
        System.out.println("4) Honor roll report");
        System.out.print("Pick: ");
        String c = sc.nextLine().trim();
        if (c.equals("1")) {
            System.out.print("StudentId: ");
            rg.generateStudentReport(sc.nextLine().trim(), sm, em);
        } else if (c.equals("2")) {
            System.out.print("CourseCode: ");
            rg.generateCourseReport(sc.nextLine().trim(), cm, em);
        } else if (c.equals("3")) {
            System.out.print("Major: ");
            rg.generateMajorReport(sc.nextLine().trim(), sm);
        } else if (c.equals("4")) {
            System.out.print("Min GPA: ");
            double min = parseDouble(sc.nextLine().trim());
            rg.generateHonorRollReport(sm, min);
        } else {
            System.out.println("Invalid.");
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Add Student");
        System.out.println("2. Remove Student");
        System.out.println("3. Find Student");
        System.out.println("4. List All Students");
        System.out.println("5. Add Course");
        System.out.println("6. Enroll Student in Course");
        System.out.println("7. Assign Grade");
        System.out.println("8. Calculate Student GPA");
        System.out.println("9. Generate Reports");
        System.out.println("10. Exit");
        System.out.print("Choice: ");
    }

    private static void seedData(StudentManager sm, CourseManager cm, EnrollmentManager em) {
        sm.addStudent(new Student("S001", "Alice", "Smith", "alice@university.edu", 3.8, "Computer Science", 2));
        sm.addStudent(new Student("S002", "Bob", "Jones", "bob@university.edu", 3.5, "Mathematics", 3));
        sm.addStudent(new Student("S003", "Charlie", "Brown", "charlie@university.edu", 3.9, "Computer Science", 2));
        sm.addStudent(new Student("S004", "Dina", "Khan", "dina@university.edu", 3.2, "Biology", 1));
        sm.addStudent(new Student("S005", "Evan", "Lee", "evan@university.edu", 2.9, "Mathematics", 4));
        sm.addStudent(new Student("S006", "Faye", "Patel", "faye@university.edu", 3.6, "Computer Science", 3));
        sm.addStudent(new Student("S007", "Gabe", "Ng", "gabe@university.edu", 3.1, "History", 2));
        sm.addStudent(new Student("S008", "Hana", "Ali", "hana@university.edu", 3.7, "Computer Science", 4));
        sm.addStudent(new Student("S009", "Ivan", "Chen", "ivan@university.edu", 2.5, "Biology", 1));
        sm.addStudent(new Student("S010", "Jade", "Miller", "jade@university.edu", 3.0, "History", 2));

        Course ds = new Course("CISC3130", "Data Structures", 3, "Dr. Smith", 30);
        ds.addPrerequisite("CISC1115");
        Course calc = new Course("MATH101", "Calculus I", 4, "Dr. Johnson", 25);
        Course intro = new Course("CISC1115", "Intro Programming", 3, "Dr. Nguyen", 35);
        Course db = new Course("CISC3220", "Databases", 3, "Dr. Patel", 25);
        db.addPrerequisite("CISC1115");
        Course bio = new Course("BIOL1100", "General Biology", 4, "Dr. Stone", 40);

        cm.addCourse(ds);
        cm.addCourse(calc);
        cm.addCourse(intro);
        cm.addCourse(db);
        cm.addCourse(bio);

        em.enrollStudent("S001", "CISC1115", "Spring 2024");
        em.enrollStudent("S001", "CISC3130", "Fall 2024");
        em.enrollStudent("S002", "MATH101", "Fall 2024");
        em.enrollStudent("S003", "CISC1115", "Spring 2024");
        em.enrollStudent("S006", "CISC1115", "Spring 2024");
        em.enrollStudent("S006", "CISC3220", "Fall 2024");

        em.assignGrade("E001", "A");
        em.assignGrade("E003", "B");
        em.assignGrade("E004", "A");
        em.assignGrade("E005", "B");
    }

    private static void runGenericShowcase(StudentManager sm) {
        GenericStack<String> st = new GenericStack<>();
        st.push("First");
        st.push("Second");

        GenericQueue<Integer> q = new GenericQueue<>();
        q.enqueue(10);
        q.enqueue(20);

        GenericList<Student> gl = new GenericList<>();
        for (Student s : sm.getAllStudentsCopy()) gl.add(s);

        ArrayList<Student> copy = sm.getAllStudentsCopy();
        ArrayList<Student> honors = ArrayListUtils.filter(copy, x -> x.getGpa() >= 3.7);
        ArrayListUtils.reverse(honors);

        Pair<String, Double> p = new Pair<>("CISC3130", 4.0);

        System.out.println("GENERIC SHOWCASE");
        System.out.println("Stack pop: " + st.pop());
        System.out.println("Queue dequeue: " + q.dequeue());
        System.out.println("GenericList size: " + gl.size());
        System.out.println("Filtered honors count: " + honors.size());
        System.out.println("Pair example: " + p);
    }

    private static int parseInt(String s) {
        try { return Integer.parseInt(s); } catch (Exception e) { return 0; }
    }

    private static double parseDouble(String s) {
        try { return Double.parseDouble(s); } catch (Exception e) { return 0.0; }
    }
}
