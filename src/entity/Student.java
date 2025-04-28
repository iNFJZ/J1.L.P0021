/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author iNJZ
 */
public class Student {

    private String id;
    private String studentName;
    private String semester;
    private Course courseName;

    public enum Course {
        JAVA, DOTNET, C;

        public int getIntValue() {
            switch (this) {
                case JAVA:
                    return 0;
                case DOTNET:
                    return 1;
                case C:
                    return 2;
            }
            throw new IndexOutOfBoundsException("Invalid course name!");
        }

        public static Course getCourseByCourseId(int courseId) {
            switch (courseId) {
                case 0:
                    return JAVA;
                case 1:
                    return DOTNET;
                case 2:
                    return C;
            }
            throw new AssertionError("Invalid course id!");
        }
    }

    public Student() {
    }

    public Student(String id, String studentName, String semester, Course courseName) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Course getCourseName() {
        return courseName;
    }

    public void setCourseName(Course courseName) {
        this.courseName = courseName;
    }

}
