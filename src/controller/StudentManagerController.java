/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bo.StudentInputer;
import bo.StudentManager;
import entity.Student;
import entity.Student.Course;
import java.util.ArrayList;
import java.util.List;
import utils.ValidationAndNormalizingTextUtil;

/**
 *
 * @author iNJZ
 */
public class StudentManagerController {

    private StudentManager studentManager;
    private StudentInputer studentInputer;

    public StudentManagerController() {
        studentManager = new StudentManager();
        studentInputer = new StudentInputer();
    }

    public Student addStudent() {
        Student student = new Student();
        studentInputer.inputCommonInformation(student);
        if (studentManager.addStudent(student)) {
            return student;
        };
        return null;
    }

    public List<Student> updateOrDelete() throws Exception {
        String idStr = ValidationAndNormalizingTextUtil.getNonEmptyString("Enter student ID: ");

        List<Student> studentsWithId = studentManager.getAllStudentsById(idStr);

        if (studentsWithId.isEmpty()) {
            throw new Exception("No student found with ID: " + idStr);
        }

        String actionChoice = ValidationAndNormalizingTextUtil.getStringByRegex(
                "Do you want to update (U) or delete (D) student?",
                "Please enter U or D only!",
                "[UuDd]");

        if (actionChoice.equalsIgnoreCase("U")) {
            Student selectedStudent;

            // Nếu có nhiều sinh viên với cùng ID, yêu cầu người dùng chọn một sinh viên để cập nhật
            if (studentsWithId.size() > 1) {
                System.out.println("Found " + studentsWithId.size() + " students with ID " + idStr + ":");
                for (int i = 0; i < studentsWithId.size(); i++) {
                    Student s = studentsWithId.get(i);
                    System.out.println((i + 1) + ". " + s.getStudentName()
                            + " - Semester: " + s.getSemester()
                            + " - Course: " + s.getCourseName());
                }

                int choice = ValidationAndNormalizingTextUtil.getInt(
                        "Select student to update (1-" + studentsWithId.size() + "): ",
                        "Please enter a valid number!",
                        "Choice must be between 1 and " + studentsWithId.size(),
                        1, studentsWithId.size());

                selectedStudent = studentsWithId.get(choice - 1);
            } else {
                selectedStudent = studentsWithId.get(0);
            }

            Student updatedInfo = new Student();
            updatedInfo.setId(selectedStudent.getId());

            // Lấy thông tin cập nhật từ người dùng
            updatedInfo.setStudentName(ValidationAndNormalizingTextUtil.getStringByRegex(
                    "Enter Student Name: ",
                    "Please enter character only!",
                    "[A-Za-z ]+"));

            updatedInfo.setSemester(ValidationAndNormalizingTextUtil.getNonEmptyString("Enter Semester: "));

            int courseChoice = ValidationAndNormalizingTextUtil.getInt(
                    "Enter your choice (1-3): ",
                    "Please enter a valid number!",
                    "Choice must be between 1 and 3",
                    1, 3);

            updatedInfo.setCourseName(Student.Course.getCourseByCourseId(courseChoice - 1));

            Student updatedStudent = studentManager.updateStudent(idStr, updatedInfo);
            List<Student> result = new ArrayList<>();
            result.add(updatedStudent);
            return result;
        } else {
            // Xóa tất cả sinh viên có cùng ID
            System.out.println("This will delete ALL students with ID " + idStr);
            boolean confirm = ValidationAndNormalizingTextUtil.pressYNtoContinue("Are you sure you want to delete? (Y/N)");
            if (confirm) {
                return studentManager.deleteStudentById(idStr);
            } else {
                System.out.println("Delete operation cancelled.");
                return new ArrayList<>();
            }
        }
    }

    public List<Student> searchByName() {
        String name = ValidationAndNormalizingTextUtil.getNonEmptyString("Enter student name to search: ");
        return studentManager.searchByName(name);
    }

    public List<String> generateReport() {
        return studentManager.generateReport();
    }

    public boolean canContinueAdding() {
        return studentManager.canContinueAdding();
    }

    public int getCurrentStudentCount() {
        return studentManager.getCurrentStudentCount();
    }

    public Course[] getCourseOptions() {
        return studentManager.getCourseOptions();
    }

//    public Student addStudent(){
//        studentInputer = new StudentInputer();
//        Student student = studentInputer.inputStudentInformation();
//        if (studentManager.addStudent(student)){
//            return student;
//        }
//        return null;
//        
//    }
//    public List<Student> addStudents() {
//        List<Student> students = new ArrayList<>();
//        do {
//            Student student = addStudent();
//            if (student != null) {
//                students.add(student);
//            }
//        } while (ValidationAndNormalizingTextUtil.pressYNtoContinue("Do you want to continue (Y/N)?"));
//        
//        return students;
//    }
}
