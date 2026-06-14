
import java.util.*;

// Student class inherits Person class
class Student extends Person {

    private int rollno; // Store Roll Number

    public Student(int rollno, String name) {
        super(name);
        this.rollno = rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollno() {
        return rollno;
    }

    // Method for Display Information
    @Override
    public void display() {
        System.out.println("Name:        " + name);
        System.out.println("Roll Number: " + rollno);
        System.out.println("Course:      BCA");
        System.out.println("Year:        2nd");
        System.out.println("Session:     2025-28");
        System.out.println("--------------------\n");
    }

}

// Handles all student management operations
class Student_Management {
    FileHandler fh = new FileHandler();
    ArrayList<Student> students = new ArrayList<>();

    // Method to Store Student Information
    // Add new student information
    public void addStudents(Scanner sc) {
        int rollno = 0;
        String name;
        boolean exist;
        do {
            try {
                exist = false;
                System.out.print("Enter Roll Number: ");
                rollno = sc.nextInt();

                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getRollno() == rollno) {

                        System.out.println("Roll Number Already Exist!");

                        exist = true;
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Roll Number must be Integer!");

                sc.nextLine();
                exist = true; // Repeat again and again till roll number is not duplicate
            }
        } while (exist);

        // Buffer
        sc.nextLine();

        // Store Name
        do {
            System.out.print("Enter Name: ");
            name = sc.nextLine();

            // Check that name is a String or NOT
            if (!name.matches("[a-zA-z ]+")) {
                System.out.println("Invalid Input!");
            }

        } while (!name.matches("[a-zA-Z ]+"));

        // Object of Student Class
        Student s = new Student(rollno, name);

        students.add(s); // Add Student info in Array list

        fh.saveToFile(students); // Save to File

        System.out.println("\nStudent Added Successfully!\n");

    }

    // Method View Student Information
    // Display all students
    public void displayStudents() {

        System.out.println("\n---Viewing... Students---\n");

        // If List is Empty
        if (students.isEmpty()) {

            System.out.println("No Student Found!");

        } else {

            for (Student st : students) {
                st.display();
            }
        }
    }

    // Method to Delete Student Information
    // Delete student using name or roll number
    public void deleteStudents(Scanner sc) {
        int delete_roll = 0;
        String delete_name;
        byte del_choice = 0;
        String warn = "";

        sc.nextLine();
        System.out.print( "Are You Sure You Want To Delete? (Y/N): ");
        warn = sc.nextLine();

        if (warn.equalsIgnoreCase("Y")) {
            // Choose deletion option
            System.out.println("1.Delete Using Name");
            System.out.println("2.Delete Using Roll Number");

            // Input Selection Of User for deletion
            try {

                del_choice = sc.nextByte();
                sc.nextLine();

            } catch (Exception e) {

                System.out.println("Choice must be Integer!\n");

                sc.nextLine();

            }

            switch (del_choice) {
                case 1 -> {

                    // Choice For Name
                    do {
                        System.out.print("Enter Name: ");
                        delete_name = sc.nextLine();

                        // Check that name is a String or NOT
                        if (!delete_name.matches("[a-zA-z ]+")) {
                            System.out.println("Invalid Input!");
                        }

                    } while (!delete_name.matches("[a-zA-Z ]+"));

                    // Found name and deleting information
                    boolean found1 = false;

                    for (int i = 0; i < students.size(); i++) {

                        System.out.println(students.get(i).getName());
                        System.out.println(delete_name);

                        if (students.get(i).getName().equalsIgnoreCase(delete_name)) {
                            students.remove(i); // Delete Students Info

                            fh.saveToFile(students); // Save Changes

                            System.out.println("Student Delete Successfully!\n");

                            found1 = true;
                            break;
                        }
                    }
                    if (!found1) {
                        System.out.println("Name Not Found!\n");
                    }
                }

                case 2 -> {

                    // Choice For Roll Number
                    boolean foundr = false;
                    try {

                        System.out.print("Enter Roll Number: ");
                        delete_roll = sc.nextInt();

                    } catch (Exception e) {

                        System.out.println("Roll Number must be Integer");

                        sc.nextLine();
                    }

                    // Found Roll Number and deleting information

                    for (int i = 0; i < students.size(); i++) {

                        if (students.get(i).getRollno() == delete_roll) {

                            students.remove(i); // Delete Students Info

                            fh.saveToFile(students); // Save Changes

                            System.out.println("Student Delete Successfully!\n");

                            foundr = true;
                            break;
                        }
                    }
                    if (!foundr) {
                        System.out.println("Roll Number Not Found!\n");
                    }

                }
                default -> System.out.println("Invalid Choice!");

            }

        } else {
            System.out.println("Deletion Cancelled!\n");

        }

    }

    // Search Student info
    // Search student using roll number
    public void searchStudent(Scanner sc) {
        int search_rollno;
        try {
            System.out.println("Enter Roll Number to Search Student Information");
            search_rollno = sc.nextInt();

            boolean sfound = false;

            for (int i = 0; i < students.size(); i++) {

                if (students.get(i).getRollno() == search_rollno) {

                    System.out.println("\n--Student Found--");
                    students.get(i).display();

                    sfound = true;

                    break;
                }
            }

            if (!sfound) {
                System.out.println("Student Not Found!");

            }

        } catch (Exception e) {

            System.out.println("Roll Number Must be Integer");
            sc.nextLine();
        }

    }

    // Update Students Information
    // Update student information
    public void updateStudents(Scanner sc) {
        int up_rollno;
        String newName;
        boolean ufound = false;

        try {

            System.out.println("Enter Roll Number to Update Information");
            up_rollno = sc.nextInt();

            sc.nextLine();

            // Check Roll Number for Updating
            for (int i = 0; i < students.size(); i++) {

                if (students.get(i).getRollno() == up_rollno) {

                    try {

                        System.out.print("Enter Name to Update:");
                        newName = sc.nextLine();

                        students.get(i).setName(newName); // Update Name

                        fh.saveToFile(students); // Save Changes

                        System.out.println("Student Updated Successfully!\n");
                    } catch (Exception e) {

                        System.out.println("Name  Must Be in Letters!");
                        sc.nextLine();
                    }

                    ufound = true;
                    break;
                }
            }

            if (!ufound) {
                System.out.println("\nRoll Number Not Found");
            }

        } catch (Exception e) {
            System.out.println("Roll Number Must Be Integer!");
            sc.nextLine();
        }

    }

    // Sorted Name According to Roll Number
    // Sort students according to roll number
    public void sortByRollno() {

        Collections.sort(students, new Comparator<Student>() { // Creates rule to compare two objects

            @Override
            public int compare(Student s1, Student s2) { // Takes two object automatically and compare them!

                return s1.getRollno() - s2.getRollno(); // It tells which student come first according to + , - , or 0
            }
        });

        fh.saveToFile(students); // Save Changes

        System.out.println("Students Sorted By Roll Number!\n");
    }

    // Searching By Name
    // Search students using partial name
    public void searchName(Scanner sc) {
        String name;
        boolean found = false;

        sc.nextLine();

        do {
            System.out.println("Enter Name ");
            name = sc.nextLine().trim();

            System.out.println("");

            // Check that name is a String or NOT
            if (!name.matches("[a-zA-Z ]+")) {
                System.out.println("Invalid Input!");
            }

        } while (!name.matches("[a-zA-Z ]+"));

        // Partially Searching Name of Students
        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getName().toLowerCase().contains(name.toLowerCase())) {

                students.get(i).display();

                found = true;
            }

        }

        if (!found) {
            System.out.println("Student NOT Found! ");
        }
    }

    //Total Students
    // Display total number of students
    public void totalStudent() {
        System.out.println("Total Student " + students.size() + "\n");
    }
}

// Driver class of Student Management System
public class Main {

    @SuppressWarnings("ConvertToTryWithResources")
    // Program execution starts from here
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        // ArrayList<Student> students = new ArrayList<>();

        Student_Management sm = new Student_Management();

        sm.fh.readFromFile(sm.students);

        byte choice;
        do {
            System.out.println("\n----------STUDENT MANAGEMENT SYSTEM----------\n");
            System.out.println("1.ADD Students");
            System.out.println("2.VIEW Students");
            System.out.println("3.Search Students");
            System.out.println("4.Search Using Name Students");
            System.out.println("5.Delete Student");
            System.out.println("6.Update Students");
            System.out.println("7.Sorting Students");
            System.out.println("8.Total Students");
            System.out.println("9.Exit\n");

            try {
                System.out.print("Enter Your Choice: ");
                choice = sc.nextByte();

            } catch (Exception e) {
                System.out.println("Invalid Input!");

                sc.nextLine();
                choice = 0;
            }

            switch (choice) {
                // Store Information
                case 1 -> sm.addStudents(sc);

                // View Student Information
                case 2 -> sm.displayStudents();

                // Search Students Information
                case 3 -> sm.searchStudent(sc);

                // Searching By Name
                case 4 -> sm.searchName(sc);

                // Delete Student info
                case 5 -> sm.deleteStudents(sc);

                // Update Students Information
                case 6 -> sm.updateStudents(sc);

                // Sorted Name According to Roll Number
                case 7 -> sm.sortByRollno();

                case 8 -> sm.totalStudent();

                case 9 ->
                    System.out.println("\nExiting...Program");

                default ->
                    System.out.println("\nInvalid Choice!");
            }
        } while (choice != 9);
        sc.close();

    }
}
