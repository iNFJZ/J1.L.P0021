/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import entity.Student;
import utils.ValidationAndNormalizingTextUtil;

/**
 *
 * @author iNJZ
 */
public class StudentInputer {
    
    private Student student;
    
    public Student inputCommonInformation() {
        student = new Student();
        student.setId(ValidationAndNormalizingTextUtil.getNonEmptyString("Enter Student ID: "));
        student.setStudentName(ValidationAndNormalizingTextUtil.getStringByRegex("Enter Student Name: ", "Please enter character only!", "[A-Za-z ]+"));
        student.setSemester(ValidationAndNormalizingTextUtil.getNonEmptyString("Enter Semester: "));
        int choice = ValidationAndNormalizingTextUtil.getInt("Enter your choice (1-3): ", "Please enter a valid number!", "Choice must be between 1 and 3", 1, 3);
        student.setCourseName(Student.Course.getCourseByCourseId(choice - 1));
        return student;
    }
}
