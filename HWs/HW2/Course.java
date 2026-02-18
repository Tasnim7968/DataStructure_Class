import java.util.ArrayList;

public class Course {
    private String courseCode;
    private String courseName;
    private int credits;
    private String instructor;
    private int maxEnrollment;
    private ArrayList<String> prerequisites;

    public Course(String courseCode, String courseName, int credits, String instructor, int maxEnrollment) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        setCredits(credits);
        this.instructor = instructor;
        setMaxEnrollment(maxEnrollment);
        this.prerequisites = new ArrayList<>();
    }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) {
        if (credits < 0) credits = 0;
        this.credits = credits;
    }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public int getMaxEnrollment() { return maxEnrollment; }
    public void setMaxEnrollment(int maxEnrollment) {
        if (maxEnrollment < 0) maxEnrollment = 0;
        this.maxEnrollment = maxEnrollment;
    }

    public void addPrerequisite(String courseCode) {
        if (courseCode == null) return;
        if (!prerequisites.contains(courseCode)) prerequisites.add(courseCode);
    }

    public boolean hasPrerequisite(String courseCode) {
        if (courseCode == null) return false;
        return prerequisites.contains(courseCode);
    }

    public ArrayList<String> getPrerequisites() {
        return new ArrayList<>(prerequisites);
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-22s | %2d | %-14s | max=%3d | prereq=%s",
                courseCode, courseName, credits, instructor, maxEnrollment, prerequisites);
    }
}
