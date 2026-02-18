import java.util.ArrayList;

public class StudentManager {
    private ArrayList<Student> students;

    public StudentManager() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (student == null || student.getStudentId() == null) return;
        if (findStudent(student.getStudentId()) != null) return;
        students.add(student);
    }

    public boolean removeStudent(String studentId) {
        Student s = findStudent(studentId);
        if (s == null) return false;
        return students.remove(s);
    }

    public Student findStudent(String studentId) {
        if (studentId == null) return null;
        for (Student s : students) {
            if (studentId.equals(s.getStudentId())) return s;
        }
        return null;
    }

    public ArrayList<Student> getStudentsByMajor(String major) {
        ArrayList<Student> out = new ArrayList<>();
        if (major == null) return out;
        for (Student s : students) {
            if (major.equalsIgnoreCase(s.getMajor())) out.add(s);
        }
        return out;
    }

    public ArrayList<Student> getStudentsByYear(int year) {
        ArrayList<Student> out = new ArrayList<>();
        for (Student s : students) {
            if (s.getYear() == year) out.add(s);
        }
        return out;
    }

    public ArrayList<Student> getHonorStudents(double minGpa) {
        ArrayList<Student> out = new ArrayList<>();
        for (Student s : students) {
            if (s.getGpa() >= minGpa) out.add(s);
        }
        return out;
    }

    public double getAverageGpa() {
        if (students.isEmpty()) return 0.0;
        double sum = 0.0;
        for (Student s : students) sum += s.getGpa();
        return sum / students.size();
    }

    public double getAverageGpaByMajor(String major) {
        if (major == null) return 0.0;
        double sum = 0.0;
        int count = 0;
        for (Student s : students) {
            if (major.equalsIgnoreCase(s.getMajor())) {
                sum += s.getGpa();
                count++;
            }
        }
        return count == 0 ? 0.0 : sum / count;
    }

    public void printAllStudents() {
        System.out.println("ID    | First Name    | Last Name     | Email                     | GPA  | Major              | Yr");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        for (Student s : students) System.out.println(s.toString());
        if (students.isEmpty()) System.out.println("(no students)");
    }

    public int getTotalStudents() {
        return students.size();
    }

    public ArrayList<String> getAllMajors() {
        ArrayList<String> majors = new ArrayList<>();
        for (Student s : students) {
            String m = s.getMajor();
            if (m != null && !majors.contains(m)) majors.add(m);
        }
        return majors;
    }

    public ArrayList<Student> getAllStudentsCopy() {
        return new ArrayList<>(students);
    }
}
