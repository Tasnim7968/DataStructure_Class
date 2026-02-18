import java.util.Objects;

public class Student implements Comparable<Student> {
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private double gpa;
    private String major;
    private int year;

    public Student(String studentId, String firstName, String lastName, String email,
                   double gpa, String major, int year) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setGpa(gpa);
        this.major = major;
        setYear(year);
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) {
        if (gpa < 0.0) gpa = 0.0;
        if (gpa > 4.0) gpa = 4.0;
        this.gpa = gpa;
    }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public int getYear() { return year; }
    public void setYear(int year) {
        if (year < 1) year = 1;
        if (year > 4) year = 4;
        this.year = year;
    }

    public String getFullName() {
        return (firstName == null ? "" : firstName) + " " + (lastName == null ? "" : lastName);
    }

    @Override
    public String toString() {
        return String.format("%-5s | %-12s | %-12s | %-25s | %4.2f | %-18s | %d",
                studentId, firstName, lastName, email, gpa, major, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return Objects.equals(this.studentId, other.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public int compareTo(Student o) {
        // Default Comparable: GPA ascending, then lastName, then firstName, then ID
        int c = Double.compare(this.gpa, o.gpa);
        if (c != 0) return c;
        c = safe(this.lastName).compareToIgnoreCase(safe(o.lastName));
        if (c != 0) return c;
        c = safe(this.firstName).compareToIgnoreCase(safe(o.firstName));
        if (c != 0) return c;
        return safe(this.studentId).compareToIgnoreCase(safe(o.studentId));
    }

    private static String safe(String s) { return s == null ? "" : s; }
}
