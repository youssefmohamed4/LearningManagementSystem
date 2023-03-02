package Program;

import Lists.CourseList;
import School.Course;
import School.Student;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONObject;
import java.io.File;
import java.util.List;

import static School.Course.getCourseList;
import static Lists.CourseList.buildCourseList;
import static School.Student.getStudentList;
import static Lists.StudentList.getDataFile;
import static Lists.StudentList.getdataList;

public class Main {
    public static void main(String[] args) throws Exception {
        getdataList(getDataFile(new File("C:\\Work Files\\Udacity Frontend Testing\\Trials\\LearningManagementSystem\\student-data.txt")));
        buildCourseList();
        HomePage();
        try {
            UsrInterface();
        }catch (Exception e) {
            System.out.println("Please enter a valid Student ID");
            UsrInterface();
        }
    }

    public static List<Student> StudentList;

    static {
        try {
            StudentList = getStudentList("C:\\Work Files\\Udacity Frontend Testing\\Trials\\LearningManagementSystem\\studentList.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void UsrInterface() throws Exception {

        for (Student student : StudentList) {
            System.out.println(student.toString());
        }
        //TODO: ask user to select student
        System.out.println("Please select a student");
        int studentID = Integer.parseInt(new Scanner(System.in).nextLine());
        StudentDetails(studentID);
        for (Student student : StudentList) {
            if (studentID != student.getId()) {
                throw new Exception("Please enter a Valid student ID");
            }
        }
    }

    public static void StudentDetails(int studentID) throws Exception {
        //TODO: Print Banner
        System.out.println("====================================================================================\n" +
                "Student Details page\n" +
                "====================================================================================");

        for (Student student : StudentList) {
            if (student.getId() == studentID) {
                System.out.println("Name: " + student.getName() + "         Grade:" + student.getGrade() + "                Email:" + student.getEmail());
                System.out.println("------------------------------------------------------------------------------------\n" +
                        "Enrolled courses.");
                break;
            }
        }
        //TODO: Print Courses List for student
        //TODO: Get Student CoursesList
        if (StudentList.get(studentID).getCourseList().size() > 0) {
            StudentList.get(studentID).getCourseList().forEach(System.out::println);
        } else {
            System.out.println("The student hasn't enrolled in any course yet.");
        }
        Options(studentID);


        // if student enrolled courses =0
        //System.out.println("The student hasn't enrolled in any courses yet")
        System.out.println("-----------------------------------------------------------------------------------------\n");


    }

    public static void Options(int studentID) throws Exception {
        //TODO: Print Options
        System.out.println("Please select an option" + "\n" + "a - Enroll in a course" + "\n" + "d - UnEnroll from an existing course" + "\n" + "r - Replacing an existing course" + "\n" + "b - Back to the main page" + "\n" + "please select the required action:");
        String option = new Scanner(System.in).nextLine();

        switch (option) {
            case "a":
                System.out.println("Enrollment page" + "\n"
                        + "====================================================================================================\nid,     Course Name,         Instructor,        Course duration,        Course time,        Location ");
                //TODO: View All Courses available
                for (Course c : Course.getCourseList()) {
                    System.out.println(c.toString());
                }
                Main.Enrollment(studentID);
                break;
            case "d":
                // if student enrolled courses =0,1 exception
                UnEnrollment(studentID);
                break;
            case "r":
                ReplaceCourse(studentID);
                break;
            case "b":
                HomePage();
                UsrInterface();
                break;
        }
    }

    private static void HomePage() {
        System.out.println("Welcome to LMS\n" +
                "created by Youssef Mohamed 23-1-2023\n" +
                "====================================================================================\n" +
                "Home page\n" +
                "====================================================================================\n" +
                "Student list:\n" +
                "id\tname\tGrade\temail\taddress\tregion\tcountry");
    }

    private static void Enrollment(int studentID) throws Exception {
        System.out.println("Please make one of the following:");
        System.out.println("Enter the course id that you want to enroll the student to");
        System.out.println("Enter b to go back to the home page");
        //Please select the required action:1
        System.out.println("Please select the required action:");
        String courseID = new Scanner(System.in).nextLine();

        if (courseID.equals("b")) {
            Main.UsrInterface();
        } else {
            //TODO: Add Course to student
                for (Course course : getCourseList()) {
                    if (course.getId() == Integer.parseInt(courseID)) {
                        try {
                            Main.StudentList.get(studentID).addCourse(course);
                            System.out.println("Enrolled successfully");
                        }catch (Exception e) {System.out.println(e.getMessage());}
                        //TODO: Print All Students With Courses in JSON Format
                        List<JSONObject> StudentJsonList = new ArrayList<>();
                        for (Student student : Main.StudentList) {
                            JSONObject StudentJson = new JSONObject();
                            List<Integer> courceList = new ArrayList<>();
                            for (int i = 0; i < student.getCourseList().size(); i++) {
                                int c = student.getCourseList().get(i).getId();
                                courceList.add(c);
                            }
                            StudentJson.put(student.getId()-1, courceList);
                            StudentJsonList.add(StudentJson);
                        }

                        try {
                            FileWriter file = new FileWriter("output.json");
                            file.write(StudentJsonList.toString());
                            file.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }else if (Integer.parseInt(courseID) > 17 ) {
                        System.out.println("Please enter a valid ID");
                        Enrollment(studentID);}
//                    }
//
                    }
                }
            Main.Enrollment(studentID);

        }

    private static void UnEnrollment(int studentID)  {
        StudentList.get(studentID).getCourseList().forEach(System.out::println);
        System.out.println("Enter course id:");
        String courseID = new Scanner(System.in).nextLine();
        //TODO: remove Course to student
        for (Course course : Main.StudentList.get(studentID).getCourseList()) {
            if (course.getId() == Integer.parseInt(courseID)) {
                try {
                    Main.StudentList.get(studentID).removeCourse(course);
                    System.out.println("Un enrolled successfully from " + course.getCourseName() + " course");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                //TODO: Print All Students With Courses in JSON Format
                List<JSONObject> StudentJsonList = new ArrayList<>();
                for (Student student : Main.StudentList) {
                    JSONObject StudentJson = new JSONObject();
                    List<Integer> courceList = new ArrayList<>();
                    for (int i = 0; i < student.getCourseList().size(); i++) {
                        int c = student.getCourseList().get(i).getId();
                        courceList.add(c);
                    }
                    StudentJson.put(student.getId()-1, courceList);
                    StudentJsonList.add(StudentJson);
                }

                try {
                    FileWriter file = new FileWriter("output.json");
                    file.write(StudentJsonList.toString());
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }try {
            StudentDetails(studentID);
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    private static void ReplaceCourse(int studentID) throws Exception {

//            StudentList.get(studentID).getCourseList().forEach(System.out::println);
            System.out.println("Please enter the required course id to be replaced:");
            String courseID = new Scanner(System.in).nextLine();
            //TODO: replace Course to student
            System.out.println("Please enter the required course id to replace:");
            String scanner = new Scanner(System.in).nextLine();
            Course target = new Course();
        for (Course course1 : getCourseList()) {
            if (course1.getId() == Integer.parseInt(scanner)) {
                target = course1;
            }}
                for (Course course : getCourseList()) {
                    if (course.getId() == Integer.parseInt(courseID)) {
                        Main.StudentList.get(studentID).replaceCourse(course,target);
                        System.out.println("Courses replaced successfully from the " + course.getCourseName() + " to " + target.getCourseName()+" course");
                        //TODO: Print All Students With Courses in JSON Format
                        List<JSONObject> StudentJsonList = new ArrayList<>();
                        for (Student student : Main.StudentList) {
                            JSONObject StudentJson = new JSONObject();
                            List<Integer> courceList = new ArrayList<>();
                            for (int i = 0; i < student.getCourseList().size(); i++) {
                                int c = student.getCourseList().get(i).getId();
                                courceList.add(c);
                            }
                            StudentJson.put(student.getId()-1, courceList);
                            StudentJsonList.add(StudentJson);
                        }

                        try {
                            FileWriter file = new FileWriter("output.json");
                            file.write(StudentJsonList.toString());
                            file.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }

                }
                StudentDetails(studentID);

            }

        }

