/** Enrolment - records that a Student is taking a Course, and the marks. */
public class Enrolment {

    private Student student;
    private Course course;
    private double marks;          // -1 means "not entered yet"

    public Enrolment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.marks = -1;
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public double getMarks() { return marks; }
    public void setMarks(double marks) {
        if (marks >= 0 && marks <= 100) this.marks = marks;
    }
    public boolean hasMarks() { return marks >= 0; }

    // Convert marks to a grade using if / else
    public String getGrade() {
        if (!hasMarks()) return "N/A";
        else if (marks >= 80) return "A";
        else if (marks >= 70) return "B";
        else if (marks >= 60) return "C";
        else if (marks >= 50) return "D";
        else return "F";
    }
}