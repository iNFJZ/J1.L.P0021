/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import entity.Student;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import utils.ValidationAndNormalizingTextUtil;

/**
 *
 * @author iNJZ
 */
public class StudentManager {

    private List<Student> students;
    private int lastId;
    private String[] courses;

    public StudentManager() {
        students = new ArrayList<>();
        lastId = 0;
        this.courses = new String[]{"Java", ".Net", "C/C++"};
    }

    public StudentManager(List<Student> students) {
        this.students = students;
    }

    public String[] getCourseOptions() {
        return courses;
    }

    public boolean canContinueAdding() {
        return students.size() < 10;
    }
    
    public int getCurrentStudentCount() {
        return students.size();
    }
    
    public boolean addStudent(Student student) {
        student.setId(++lastId);
        return students.add(student);
    }

    public Student updateStudent(int id, Student student) throws Exception {
        int index = searchById(id);
        if (index != -1) {
            student.setId(id);
            students.set(index, student);
            return student;
        }
        throw new Exception("Student not found!");
    }

    public Student deleteStudentById(int id) throws Exception {
        int index = searchById(id);
        if (index != -1) {
            return students.remove(index);
        }
        throw new Exception("Student does not exist!");
    }

    public Student getStudentById(int id) throws Exception {
        int index = searchById(id);
        if (index != -1) {
            return students.get(index);
        }
        throw new Exception("Id not found");
    }

//    public List<Student> searchByName(String name) {
//        ArrayList<Student> result = new ArrayList<>();
//        for (Student student : students) {
//            if (student.getStudentName().toLowerCase().contains(name.toLowerCase())) {
//                result.add(student);
//            }
//        }
//        return result;
//    }
    
    public List<Student> searchByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getStudentName().toLowerCase().contains(name.toLowerCase())) {
                result.add(student);
            }
        }
        Collections.sort(result, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getStudentName().compareTo(s2.getStudentName());
            }
        });
        return result;
    }

    public List<String> generateReport() {
        List<String> report = new ArrayList<>();
        for (Student student : students) {
            String studentName = student.getStudentName();
            String courseName = student.getCourseName();
            boolean found = false;
            for (int i = 0; i < report.size(); i++) {
                String line = report.get(i);
                if (line.startsWith(studentName + " | ")) {
                    String[] parts = line.split(" \\| ");
                    int count = Integer.parseInt(parts[2]);
                    report.set(i, studentName + " | " + courseName + " | " + (count + 1));
                    found = true;
                    break;
                }
            }
            if (!found) {
                report.add(studentName + " | " + courseName + " | 1");
            }
        }
        return report;
    }

    private int searchById(int id) {
        for (int index = 0; index < students.size(); index++) {
            if (students.get(index).getId() == id) {
                return index;
            }
        }
        return -1;
    }
    
    public List<Student> getStudents(){
        return students;
    }
}
