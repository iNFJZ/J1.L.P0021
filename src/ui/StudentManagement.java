/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ui;

import controller.StudentManagerController;
import entity.Student;
import java.util.List;
import utils.ValidationAndNormalizingTextUtil;

/**
 *
 * @author iNJZ
 */
public class StudentManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StudentManagerController controller = new StudentManagerController();
        String menu = """
                      WELCOME TO STUDENT MANAGEMENT
                      1. Create
                      2. Find and Sort
                      3. Update/Delete
                      4. Report
                      5. Exit
                      
                      (Please choose 1 to Create, 2 to Find and Sort, 3 to Update/Delete, 4 to Report and 5 to Exit program).
                      Your choice: """;
        while (true) {
            int choice = ValidationAndNormalizingTextUtil.getInt(menu, "Must input an integer number!", "Must input an integer in range [1,5]", 1, 5);
            switch (choice) {
                case 1:
                    controller.addStudent();
                    break;
                case 2:
                    List<Student> foundStudents = controller.searchByName();
                    if (foundStudents.isEmpty()) {
                        System.out.println("No student found!");
                    } else {
                        System.out.println("Found students:");
                        for (Student student : foundStudents) {
                            System.out.println(student);
                        }
                    }
                    break;
                case 3:
                    try {
                        Student result = controller.updateOrDelete();
                        if (result != null) {
                            System.out.println("Update or Delete completed successfully");
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    List<String> report = controller.generateReport();
                    if (report.isEmpty()) {
                        System.out.println("No data to report!");
                    } else {
                        System.out.println("Student report:");
                        for (String line : report) {
                            System.out.println(line);
                        }
                    }
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    throw new AssertionError();
            }
        }
    }
}
