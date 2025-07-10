import java.util.ArrayList;
import java.util.Scanner;

public class studentgradetracker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\nStudent Grade Tracker Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Display Summary Report");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent(scanner, students);
                    break;
                case 2:
                    displaySummaryReport(students);
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent(Scanner scanner, ArrayList<Student> students) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        ArrayList<Integer> grades = new ArrayList<>();
        while (true) {
            System.out.print("Enter grade (or 'done' to finish): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            try {
                int grade = Integer.parseInt(input);
                grades.add(grade);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number or 'done'.");
            }
        }
        students.add(new Student(name, grades));
        System.out.println("Student added successfully.");
    }

    private static void displaySummaryReport(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }

        System.out.println("\n--- Student Summary Report ---");
        for (Student student : students) {
            System.out.println("Name: " + student.getName());
            if (!student.getGrades().isEmpty()) {
                System.out.println("Grades: " + student.getGrades());
                System.out.println("Average Grade: " + student.calculateAverage());
                System.out.println("Highest Grade: " + student.findHighestGrade());
                System.out.println("Lowest Grade: " + student.findLowestGrade());
            } else {
                System.out.println("No grades recorded for this student.");
            }
            System.out.println("--------------------");
        }
    }
}

class Student {
    private String name;
    private ArrayList<Integer> grades;

    public Student(String name, ArrayList<Integer> grades) {
        this.name = name;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getGrades() {
        return grades;
    }

    public double calculateAverage() {
        if (grades.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    public int findHighestGrade() {
        if (grades.isEmpty()) {
            return 0;
        }
        int highest = grades.get(0);
        for (int grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    public int findLowestGrade() {
        if (grades.isEmpty()) {
            return 0;
        }
        int lowest = grades.get(0);
        for (int grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }
}
