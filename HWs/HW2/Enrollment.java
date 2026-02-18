public class Enrollment {
    private String enrollmentId;
    private String studentId;
    private String courseCode;
    private String grade;
    private String semester;

    public Enrollment(String enrollmentId, String studentId, String courseCode, String semester) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.semester = semester;
        this.grade = null;
    }

    public String getEnrollmentId() { return enrollmentId; }
    public void setEnrollmentId(String enrollmentId) { this.enrollmentId = enrollmentId; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public double getGradePoints() {
        if (grade == null) return 0.0;
        switch (grade.toUpperCase()) {
            case "A": return 4.0;
            case "B": return 3.0;
            case "C": return 2.0;
            case "D": return 1.0;
            case "F": return 0.0;
            default: return 0.0;
        }
    }

    public boolean isPassing() {
        if (grade == null) return false;
        String g = grade.toUpperCase();
        return g.equals("A") || g.equals("B") || g.equals("C") || g.equals("D");
    }

    @Override
    public String toString() {
        return String.format("%-5s | %-5s | %-8s | %-10s | grade=%s",
                enrollmentId, studentId, courseCode, semester, grade == null ? "N/A" : grade);
    }
}
