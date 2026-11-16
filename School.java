import java.util.ArrayList;

/**
 * School - stores all people, courses and enrolments and does the work.
 * The people list is of type Person, so it can hold both Student and
 * Lecturer objects (polymorphism).
 */
public class School {

    private ArrayList<Person> people = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Enrolment> enrolments = new ArrayList<>();
    private int studentCount = 0, lecturerCount = 0;

    // ----- Create objects -----
    public Student addStudent(String name, String email, String program) {
        studentCount++;
        Student s = new Student("S" + String.format("%03d", studentCount), name, email, program);
        people.add(s);
        return s;
    }

    public Lecturer addLecturer(String name, String email, String department) {
        lecturerCount++;
        Lecturer l = new Lecturer("L" + String.format("%03d", lecturerCount), name, email, department);
        people.add(l);
        return l;
    }

    public String addCourse(String code, String name, int creditHours) {
        if (findCourse(code) != null) return "[ERROR] Course " + code + " already exists.";
        courses.add(new Course(code.toUpperCase(), name, creditHours));
        return "[OK] Course " + code.toUpperCase() + " added.";
    }

    // ----- Search -----
    private Student findStudent(String id) {
        for (Person p : people)
            if (p instanceof Student && p.getId().equalsIgnoreCase(id)) return (Student) p;
        return null;
    }

    private Lecturer findLecturer(String id) {
        for (Person p : people)
            if (p instanceof Lecturer && p.getId().equalsIgnoreCase(id)) return (Lecturer) p;
        return null;
    }

    private Course findCourse(String code) {
        for (Course c : courses)
            if (c.getCode().equalsIgnoreCase(code)) return c;
        return null;
    }

    private Enrolment findEnrolment(Student s, Course c) {
        for (Enrolment e : enrolments)
            if (e.getStudent() == s && e.getCourse() == c) return e;
        return null;
    }

    private int countEnrolments(Student s) {
        int count = 0;
        for (Enrolment e : enrolments)
            if (e.getStudent() == s) count++;
        return count;
    }

    private int countCoursesTaught(Lecturer l) {
        int count = 0;
        for (Course c : courses)
            if (c.getLecturer() == l) count++;
        return count;
    }

    // ----- Operations -----
    public String enrolStudent(String studentId, String courseCode) {
        Student s = findStudent(studentId);
        if (s == null) return "[ERROR] Student not found: " + studentId;
        Course c = findCourse(courseCode);
        if (c == null) return "[ERROR] Course not found: " + courseCode;
        if (findEnrolment(s, c) != null)
            return "[DENIED] " + s.getName() + " is already enrolled in " + c.getCode() + ".";
        if (countEnrolments(s) >= s.getMaxCourses())
            return "[DENIED] " + s.getName() + " has reached the limit of " + s.getMaxCourses() + " courses.";

        enrolments.add(new Enrolment(s, c));
        return "[OK] " + s.getName() + " enrolled in " + c.getCode() + " - " + c.getName() + ".";
    }

    public String assignLecturer(String lecturerId, String courseCode) {
        Lecturer l = findLecturer(lecturerId);
        if (l == null) return "[ERROR] Lecturer not found: " + lecturerId;
        Course c = findCourse(courseCode);
        if (c == null) return "[ERROR] Course not found: " + courseCode;
        if (countCoursesTaught(l) >= l.getMaxCourses())
            return "[DENIED] " + l.getName() + " has reached the limit of " + l.getMaxCourses() + " courses.";

        c.setLecturer(l);
        return "[OK] " + l.getName() + " is now teaching " + c.getCode() + ".";
    }

    public String enterMarks(String studentId, String courseCode, double marks) {
        Student s = findStudent(studentId);
        if (s == null) return "[ERROR] Student not found: " + studentId;
        Course c = findCourse(courseCode);
        if (c == null) return "[ERROR] Course not found: " + courseCode;
        Enrolment e = findEnrolment(s, c);
        if (e == null) return "[ERROR] " + s.getName() + " is not enrolled in " + c.getCode() + ".";
        if (marks < 0 || marks > 100) return "[ERROR] Marks must be between 0 and 100.";

        e.setMarks(marks);
        return "[OK] Marks saved. " + s.getName() + " got grade " + e.getGrade() + " in " + c.getCode() + ".";
    }

    // ----- Display -----
    public void displayAllPeople() {
        System.out.println("\n--- ALL STUDENTS AND LECTURERS ---");
        System.out.printf("%-6s %-9s %-18s %-26s %-16s %-4s%n",
                "ID", "Role", "Name", "Email", "Program/Dept", "Max");
        System.out.println("-".repeat(84));
        for (Person p : people) {
            p.displayInfo();          // runtime polymorphism
        }
    }

    public void displayAllCourses() {
        System.out.println("\n--- ALL COURSES ---");
        System.out.printf("%-9s %-24s %-8s %-18s%n", "Code", "Course Name", "Credits", "Lecturer");
        System.out.println("-".repeat(62));
        for (Course c : courses) c.displayInfo();
    }

    public void displayResults(String studentId) {
        Student s = findStudent(studentId);
        if (s == null) {
            System.out.println("[ERROR] Student not found: " + studentId);
            return;
        }
        System.out.println("\n--- RESULTS FOR " + s.getName().toUpperCase() + " (" + s.getId() + ") ---");
        System.out.printf("%-9s %-24s %-8s %-6s%n", "Code", "Course Name", "Marks", "Grade");
        System.out.println("-".repeat(50));

        double total = 0;
        int counted = 0;
        for (Enrolment e : enrolments) {
            if (e.getStudent() == s) {
                String marksText = e.hasMarks() ? String.format("%.1f", e.getMarks()) : "-";
                System.out.printf("%-9s %-24s %-8s %-6s%n",
                        e.getCourse().getCode(), e.getCourse().getName(), marksText, e.getGrade());
                if (e.hasMarks()) { total += e.getMarks(); counted++; }
            }
        }
        if (counted > 0) System.out.printf("Average marks: %.1f%n", total / counted);
        else System.out.println("(no marks entered yet)");
    }
}