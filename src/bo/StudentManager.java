/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import entity.Student;
import entity.Student.Course;
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

    public StudentManager() {
        students = new ArrayList<>();
    }

    public boolean canContinueAdding() {
        return students.size() < 10;
    }

    public int getCurrentStudentCount() {
        return students.size();
    }

    public Student getStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudentsById(String id) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                result.add(student);
            }
        }
        return result;
    }

    public boolean addStudent(Student student) {
        return students.add(student);
    }

    public Student updateStudent(String id, Student updatedStudent) throws Exception {
        if (getStudentById(id) == null) {
            throw new Exception("Student does not exist!");
        }

        List<Student> studentsWithSameId = getAllStudentsById(id);

        if (studentsWithSameId.size() == 1) {
            Student studentToUpdate = studentsWithSameId.get(0);
            studentToUpdate.setStudentName(updatedStudent.getStudentName());
            studentToUpdate.setSemester(updatedStudent.getSemester());
            studentToUpdate.setCourseName(updatedStudent.getCourseName());
            return studentToUpdate;
        }

        // Nếu có nhiều sinh viên với cùng ID
        // 1. Cập nhật tên cho tất cả sinh viên cùng ID
        for (Student student : studentsWithSameId) {
            student.setStudentName(updatedStudent.getStudentName());
        }

        // 2. Tìm sinh viên cụ thể để cập nhật course và semester
        Student targetStudent = null;
        for (Student student : studentsWithSameId) {
            if (student.getSemester().equalsIgnoreCase(updatedStudent.getSemester())) {
                targetStudent = student;
                break;
            }
        }

        // Nếu không tìm thấy sinh viên với semester đã cho, lấy sinh viên đầu tiên
        if (targetStudent == null) {
            targetStudent = studentsWithSameId.get(0);
        }

        // 3. Cập nhật course cho sinh viên được chọn
        targetStudent.setCourseName(updatedStudent.getCourseName());

        // 4. Kiểm tra và cập nhật semester
        String newSemester = updatedStudent.getSemester();
        // Kiểm tra xem có sinh viên nào khác với cùng ID và course đã có semester này chưa
        for (Student student : studentsWithSameId) {
            if (student != targetStudent
                    && student.getCourseName() == targetStudent.getCourseName()
                    && student.getSemester().equalsIgnoreCase(newSemester)) {
                throw new Exception("Cannot update semester: A student with the same ID and course already has this semester!");
            }
        }

        targetStudent.setSemester(newSemester);

        return targetStudent;
    }

    public List<Student> deleteStudentById(String id) throws Exception {
        List<Student> studentsToDelete = getAllStudentsById(id);

        if (studentsToDelete.isEmpty()) {
            throw new Exception("Student does not exist!");
        }

        List<Student> deletedStudents = new ArrayList<>(studentsToDelete);
        students.removeAll(studentsToDelete);

        return deletedStudents;
    }

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
            Course courseName = student.getCourseName();
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
}
