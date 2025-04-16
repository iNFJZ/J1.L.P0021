/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bo.StudentInputer;
import bo.StudentManager;
import entity.Student;
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

    public void addStudent() {
        Student student = new Student();
        studentInputer.inputCommonInformation(student);
        studentManager.addStudent(student);
    }

    public Student updateOrDelete() throws Exception {
        int id = ValidationAndNormalizingTextUtil.getInt("Enter student ID: ", "Please enter a valid number", "ID must be greater than 0", 1, Integer.MAX_VALUE);
        String choice = ValidationAndNormalizingTextUtil.getStringByRegex("Do you want to update (U) or delete (D) student?", "Please enter U or D only!", "[UuDd]");
        if (choice.equalsIgnoreCase("U")) {
            Student student = studentManager.getStudentById(id);
            studentInputer.inputCommonInformation(student);
            return studentManager.updateStudent(id, student);
        } else {
            return studentManager.deleteStudentById(id);
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

    public String[] getCourseOptions() {
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
