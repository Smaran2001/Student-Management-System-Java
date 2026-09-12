import java.util.*;

public class StudentEmployeeManagementSystem {

    // Nested Custom Exception
    static class StudentNotFoundException extends Exception {
        public StudentNotFoundException(String message) {
            super(message);
        }
    }

    // Nested Student Class
    static class Student {
        private int id;
        private String name;
        private String department;
        private double gpa;

        public Student(int id, String name, String department, double gpa) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.gpa = gpa;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getDepartment() { return department; }
        public double getGpa() { return gpa; }

        @Override
        public String toString() {
            return "ID: " + id + " | Name: " + name + " | Dept: " + department + " | GPA: " + gpa;
        }
    }

    private Map<Integer, Student> studentMap = new HashMap<>();
    private List<Student> studentList = new ArrayList<>();

    public void addStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            System.out.println("Error: Student ID already exists!");
            return;
        }
        studentMap.put(student.getId(), student);
        studentList.add(student);
        System.out.println("Student record added successfully!");
    }

    public Student searchStudent(int id) throws StudentNotFoundException {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundException("Student with ID " + id + " not found!");
        }
        return studentMap.get(id);
    }

    public void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        System.out.println("\n--- All Student Records ---");
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    public void deleteStudent(int id) throws StudentNotFoundException {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundException("Student with ID " + id + " not found!");
        }
        Student s = studentMap.remove(id);
        studentList.remove(s);
        System.out.println("Student record deleted successfully!");
    }

    public static void main(String[] args) {
        StudentEmployeeManagementSystem system = new StudentEmployeeManagementSystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== In-Memory Management System Initialized ===");

        system.addStudent(new Student(101, "Smarandeep Singh", "CSE", 8.5));
        system.addStudent(new Student(102, "Rohan Sharma", "ECE", 7.8));

        while (true) {
            System.out.println("\n1. Add Record | 2. Search Record | 3. Display All | 4. Delete Record | 5. Exit");
            System.out.print("Enter Choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 5) break;

                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Dept: ");
                        String dept = scanner.nextLine();
                        System.out.print("Enter GPA: ");
                        double gpa = Double.parseDouble(scanner.nextLine());
                        system.addStudent(new Student(id, name, dept, gpa));
                        break;
                    case 2:
                        System.out.print("Enter Search ID: ");
                        int sId = Integer.parseInt(scanner.nextLine());
                        Student found = system.searchStudent(sId);
                        System.out.println("Found: " + found);
                        break;
                    case 3:
                        system.displayAllStudents();
                        break;
                    case 4:
                        System.out.print("Enter ID to Delete: ");
                        int dId = Integer.parseInt(scanner.nextLine());
                        system.deleteStudent(dId);
                        break;
                    default:
                        System.out.println("Invalid option!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input Format! Please enter numeric values.");
            } catch (StudentNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }
}