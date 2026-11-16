import java.util.Scanner;

/** Main - console menu for the Student Management System. */
public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        School school = new School();
        loadSampleData(school);

        int choice = -1;
        while (choice != 0) {
            printMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
    Student s = school.addStudent(
            readName("Name    : "), readEmail("Email   : "), readName("Program : "));
    System.out.println("[OK] Student added. ID: " + s.getId());
    break;
case 2:
    Lecturer l = school.addLecturer(
            readName("Name       : "), readEmail("Email      : "), readName("Department : "));
    System.out.println("[OK] Lecturer added. ID: " + l.getId());
    break;
                case 3:
                    System.out.println(school.addCourse(
                            readText("Course code : "), readText("Course name : "), readInt("Credit hours: ")));
                    break;
                case 4:
                    String studentId = readText("Student ID  : ");
                    String courseCode = readText("Course code : ");
                    System.out.println(school.enrolStudent(studentId, courseCode));
                    break;
                case 5:
                    String lecturerId = readText("Lecturer ID : ");
                    String code = readText("Course code : ");
                    System.out.println(school.assignLecturer(lecturerId, code));
                    break;
                case 6:
                    String sid = readText("Student ID  : ");
                    String ccode = readText("Course code : ");
                    int marks = readInt("Marks (0-100): ");
                    System.out.println(school.enterMarks(sid, ccode, marks));
                    break;
                case 7:
                    school.displayAllPeople();
                    break;
                case 8:
                    school.displayAllCourses();
                    break;
                case 9:
                    school.displayResults(readText("Student ID: "));
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("[ERROR] Invalid choice. Please enter 0-9.");
            }
        }
    }
// Keeps asking until the name contains letters only
private static String readName(String prompt) {
    while (true) {
        String text = readText(prompt);
        if (text.matches("[A-Za-z .'-]+")) return text;
        System.out.println("[ERROR] Please use letters only.");
    }
}

// Keeps asking until the email looks valid
private static String readEmail(String prompt) {
    while (true) {
        String text = readText(prompt);
        if (text.contains("@") && text.contains(".")) return text;
        System.out.println("[ERROR] Enter a valid email (example: ali@mail.com).");
    }
}

    private static void printMenu() {
        System.out.println("\n========== STUDENT MANAGEMENT SYSTEM ==========");
        System.out.println(" 1. Add Student        6. Enter Marks");
        System.out.println(" 2. Add Lecturer       7. Display All People");
        System.out.println(" 3. Add Course         8. Display Courses");
        System.out.println(" 4. Enrol Student      9. Display Student Results");
        System.out.println(" 5. Assign Lecturer    0. Exit");
        System.out.println("===============================================");
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Keeps asking until the user types a valid whole number
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Please enter a number.");
            }
        }
    }

    // A few objects so the program can be tested immediately
    private static void loadSampleData(School school) {
        school.addStudent("PIAL MD", "Pial@student.cuc.edu.my", "BIT");
        school.addStudent("Nurul Aina", "aina@student.cuc.edu.my", "DIT");
        school.addLecturer("Dr. MUSA AHMED", "Musa@cuc.edu.my", "Faculty of IT");
        school.addCourse("BIT1123", "Object Oriented Prog", 3);
        school.addCourse("BIT1013", "Database Systems", 3);
    }
}