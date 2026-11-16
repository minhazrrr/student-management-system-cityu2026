/** Lecturer - subclass of Person. A lecturer can teach up to 3 courses. */
public class Lecturer extends Person {

    private String department;

    public Lecturer(String id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public int getMaxCourses() { return 3; }          // overrides default (1)

    @Override
    public void displayInfo() {
        System.out.printf("%-6s %-9s %-18s %-26s %-16s %-4d%n",
                getId(), "Lecturer", getName(), getEmail(), department, getMaxCourses());
    }
}