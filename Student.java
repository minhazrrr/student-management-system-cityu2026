/** Student - subclass of Person. A student can take up to 5 courses. */
public class Student extends Person {

    private String program;

    public Student(String id, String name, String email, String program) {
        super(id, name, email);          // call superclass constructor
        this.program = program;
    }

    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }

    @Override
    public int getMaxCourses() { return 5; }          // overrides default (1)

    @Override
    public void displayInfo() {
        System.out.printf("%-6s %-9s %-18s %-26s %-16s %-4d%n",
                getId(), "Student", getName(), getEmail(), program, getMaxCourses());
    }
}