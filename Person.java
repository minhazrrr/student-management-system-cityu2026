/**
 * Person - ABSTRACT superclass for everyone in the school.
 * (Abstraction + Encapsulation)
 */
public abstract class Person {

    // Encapsulation: private attributes
    private String id;
    private String name;
    private String email;

    public Person(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {     // simple validation
            this.name = name;
        }
    }
    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {       // simple validation
            this.email = email;
        }
    }

    // Abstract method: every subclass MUST implement it
    public abstract void displayInfo();

    // Normal method that subclasses MAY override
    public int getMaxCourses() { return 1; }
}