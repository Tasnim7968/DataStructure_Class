import java.util.ArrayList;

public class CourseManager {
    private ArrayList<Course> courses;

    public CourseManager() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        if (course == null || course.getCourseCode() == null) return;
        if (findCourse(course.getCourseCode()) != null) return;
        courses.add(course);
    }

    public Course findCourse(String courseCode) {
        if (courseCode == null) return null;
        for (Course c : courses) {
            if (courseCode.equalsIgnoreCase(c.getCourseCode())) return c;
        }
        return null;
    }

    public ArrayList<Course> getCoursesByInstructor(String instructor) {
        ArrayList<Course> out = new ArrayList<>();
        if (instructor == null) return out;
        for (Course c : courses) {
            if (instructor.equalsIgnoreCase(c.getInstructor())) out.add(c);
        }
        return out;
    }

    public ArrayList<Course> getAvailableCourses(String studentId,
                                                 StudentManager studentManager,
                                                 EnrollmentManager enrollmentManager) {
        ArrayList<Course> out = new ArrayList<>();
        if (studentId == null || studentManager == null || enrollmentManager == null) return out;
        if (studentManager.findStudent(studentId) == null) return out;

        for (Course c : courses) {
            boolean alreadyEnrolled = false;
            for (Enrollment e : enrollmentManager.getEnrollmentsByStudent(studentId)) {
                if (c.getCourseCode().equalsIgnoreCase(e.getCourseCode())) {
                    alreadyEnrolled = true;
                    break;
                }
            }
            if (alreadyEnrolled) continue;

            boolean prereqMet = true;
            for (String prereq : c.getPrerequisites()) {
                if (!enrollmentManager.hasCompletedCourse(studentId, prereq)) {
                    prereqMet = false;
                    break;
                }
            }
            if (!prereqMet) continue;

            if (enrollmentManager.getEnrollmentCount(c.getCourseCode()) >= c.getMaxEnrollment()) continue;

            out.add(c);
        }
        return out;
    }

    public void printAllCourses() {
        System.out.println("Code     | Name                   | Cr | Instructor     | Max | Prereqs");
        System.out.println("----------------------------------------------------------------------------");
        for (Course c : courses) System.out.println(c.toString());
        if (courses.isEmpty()) System.out.println("(no courses)");
    }

    public int getTotalCourses() {
        return courses.size();
    }

    public ArrayList<Course> getAllCoursesCopy() {
        return new ArrayList<>(courses);
    }
}
