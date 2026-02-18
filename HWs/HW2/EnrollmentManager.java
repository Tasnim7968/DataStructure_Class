import java.util.ArrayList;

public class EnrollmentManager {
    private ArrayList<Enrollment> enrollments;
    private int enrollmentCounter;
    private CourseManager courseManager;

    public EnrollmentManager() {
        this.enrollments = new ArrayList<>();
        this.enrollmentCounter = 1;
        this.courseManager = null;
    }

    public EnrollmentManager(CourseManager courseManager) {
        this();
        this.courseManager = courseManager;
    }

    public void setCourseManager(CourseManager cm) {
        this.courseManager = cm;
    }

    public void enrollStudent(String studentId, String courseCode, String semester) {
        if (studentId == null || courseCode == null || semester == null) return;

        if (courseManager != null) {
            Course c = courseManager.findCourse(courseCode);
            if (c == null) return;
            if (getEnrollmentCount(courseCode) >= c.getMaxEnrollment()) return;
        }

        for (Enrollment e : enrollments) {
            if (studentId.equals(e.getStudentId()) &&
                courseCode.equals(e.getCourseCode()) &&
                semester.equalsIgnoreCase(e.getSemester())) {
                return;
            }
        }

        String id = String.format("E%03d", enrollmentCounter++);
        enrollments.add(new Enrollment(id, studentId, courseCode, semester));
    }

    public boolean dropEnrollment(String enrollmentId) {
        Enrollment e = findEnrollment(enrollmentId);
        if (e == null) return false;
        return enrollments.remove(e);
    }

    public Enrollment findEnrollment(String enrollmentId) {
        if (enrollmentId == null) return null;
        for (Enrollment e : enrollments) {
            if (enrollmentId.equals(e.getEnrollmentId())) return e;
        }
        return null;
    }

    public ArrayList<Enrollment> getEnrollmentsByStudent(String studentId) {
        ArrayList<Enrollment> out = new ArrayList<>();
        if (studentId == null) return out;
        for (Enrollment e : enrollments) {
            if (studentId.equals(e.getStudentId())) out.add(e);
        }
        return out;
    }

    public ArrayList<Enrollment> getEnrollmentsByCourse(String courseCode) {
        ArrayList<Enrollment> out = new ArrayList<>();
        if (courseCode == null) return out;
        for (Enrollment e : enrollments) {
            if (courseCode.equals(e.getCourseCode())) out.add(e);
        }
        return out;
    }

    public void assignGrade(String enrollmentId, String grade) {
        Enrollment e = findEnrollment(enrollmentId);
        if (e == null) return;
        if (!isValidGrade(grade)) return;
        e.setGrade(grade.toUpperCase());
    }

    public double calculateStudentGpa(String studentId) {
        ArrayList<Enrollment> es = getEnrollmentsByStudent(studentId);
        if (es.isEmpty()) return 0.0;

        double pointsSum = 0.0;
        int gradedCount = 0;

        for (Enrollment e : es) {
            if (e.getGrade() != null && isValidGrade(e.getGrade())) {
                pointsSum += e.getGradePoints();
                gradedCount++;
            }
        }
        return gradedCount == 0 ? 0.0 : pointsSum / gradedCount;
    }

    public ArrayList<String> getStudentsInCourse(String courseCode) {
        ArrayList<String> out = new ArrayList<>();
        if (courseCode == null) return out;
        for (Enrollment e : enrollments) {
            if (courseCode.equals(e.getCourseCode())) {
                if (!out.contains(e.getStudentId())) out.add(e.getStudentId());
            }
        }
        return out;
    }

    public int getEnrollmentCount(String courseCode) {
        int count = 0;
        if (courseCode == null) return 0;
        for (Enrollment e : enrollments) {
            if (courseCode.equals(e.getCourseCode())) count++;
        }
        return count;
    }

    public void printAllEnrollments() {
        System.out.println("EnrID | StuID | Course    | Semester   | Grade");
        System.out.println("------------------------------------------------");
        for (Enrollment e : enrollments) System.out.println(e.toString());
        if (enrollments.isEmpty()) System.out.println("(no enrollments)");
    }

    public boolean hasCompletedCourse(String studentId, String courseCode) {
        for (Enrollment e : enrollments) {
            if (studentId.equals(e.getStudentId()) && courseCode.equals(e.getCourseCode())) {
                if (e.getGrade() != null && e.isPassing()) return true;
            }
        }
        return false;
    }

    private boolean isValidGrade(String grade) {
        if (grade == null) return false;
        String g = grade.toUpperCase();
        return g.equals("A") || g.equals("B") || g.equals("C") || g.equals("D") || g.equals("F");
    }

    public ArrayList<Enrollment> getAllEnrollmentsCopy() {
        return new ArrayList<>(enrollments);
    }
}
