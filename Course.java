/** Course - a subject that students can enrol in and a lecturer can teach. */
public class Course {

    private String code;
    private String name;
    private int creditHours;
    private Lecturer lecturer;      // null until a lecturer is assigned

    public Course(String code, String name, int creditHours) {
        this.code = code;
        this.name = name;
        this.creditHours = creditHours;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getCreditHours() { return creditHours; }
    public void setCreditHours(int creditHours) {
        if (creditHours > 0) this.creditHours = creditHours;
    }
    public Lecturer getLecturer() { return lecturer; }
    public void setLecturer(Lecturer lecturer) { this.lecturer = lecturer; }

    public void displayInfo() {
        String lecturerName = (lecturer == null) ? "Not assigned" : lecturer.getName();
        System.out.printf("%-9s %-24s %-8d %-18s%n", code, name, creditHours, lecturerName);
    }
}