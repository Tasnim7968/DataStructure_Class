import java.util.ArrayList;

public class ReportGenerator {

    public void generateStudentReport(String studentId, StudentManager sm, EnrollmentManager em) {
        Student s = sm.findStudent(studentId);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("STUDENT REPORT");
        System.out.println(s.toString());
        ArrayList<Enrollment> es = em.getEnrollmentsByStudent(studentId);
        System.out.println("Enrollments:");
        for (Enrollment e : es) System.out.println("  " + e.toString());
        System.out.println("Calculated GPA (from graded enrollments): " + String.format("%.2f", em.calculateStudentGpa(studentId)));
    }

    public void generateCourseReport(String courseCode, CourseManager cm, EnrollmentManager em) {
        Course c = cm.findCourse(courseCode);
        if (c == null) {
            System.out.println("Course not found.");
            return;
        }
        System.out.println("COURSE REPORT");
        System.out.println(c.toString());
        ArrayList<Enrollment> es = em.getEnrollmentsByCourse(courseCode);
        System.out.println("Enrollments:");
        double sum = 0.0;
        int graded = 0;
        for (Enrollment e : es) {
            System.out.println("  " + e.toString());
            if (e.getGrade() != null) {
                sum += e.getGradePoints();
                graded++;
            }
        }
        System.out.println("Average grade points (graded only): " + (graded == 0 ? "N/A" : String.format("%.2f", sum / graded)));
    }

    public void generateMajorReport(String major, StudentManager sm) {
        ArrayList<Student> list = sm.getStudentsByMajor(major);
        System.out.println("MAJOR REPORT: " + major);
        for (Student s : list) System.out.println(s.toString());
        System.out.println("Average GPA: " + String.format("%.2f", sm.getAverageGpaByMajor(major)));
    }

    public void generateHonorRollReport(StudentManager sm, double minGpa) {
        ArrayList<Student> honors = sm.getHonorStudents(minGpa);
        System.out.println("HONOR ROLL (GPA >= " + minGpa + ")");
        for (Student s : honors) System.out.println(s.toString());
        if (honors.isEmpty()) System.out.println("(none)");
    }
}
