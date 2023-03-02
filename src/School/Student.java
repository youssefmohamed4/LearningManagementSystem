package School;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static Program.Main.StudentList;


public class Student {
    private String name;
    private int Grade;
    private int Id;
    private String Email;
    //address,region,country
    private String address;
    private String region;
    private String country;
    private List<Course> CourseList = new ArrayList<Course>();

    public static Student getStudent(int studentID) {
        Student st = new Student();
        for (Student s : StudentList) {
            if (s.getId() == studentID) {
                st = s;
            }
        }
        return st;
    }

    public List<Course> getCourseList() {
        return CourseList;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


    public void setCourseList(List<Course> courseList) {
        CourseList = courseList;
    }
    public void addCourse(Course course) throws Exception {
        boolean AlreadyEnrolled = false;
        for (Course c : CourseList) {
            if (c.getId() == course.getId()) {
                AlreadyEnrolled = true;
            }
        }
            if (AlreadyEnrolled) {
                throw new Exception("Can't enroll in the same course twice");
            } else if (CourseList.size() >= 6) {
                throw new Exception("Can't enroll in more than six courses");
            } else if (!CourseList.contains(course) && CourseList.size() < 6 ) {
                CourseList.add(course);
            }
        }


    public void removeCourse(Course course) throws Exception {
        for (int i = 0; i < CourseList.size(); i++) {
            if (CourseList.size() > 1) {
                if (CourseList.get(i).getId() == course.getId()) {
                    CourseList.remove(i);
                }
            }else {throw new Exception("Student should be enrolled in at least one course"); }
        }
    }
    public void replaceCourse(Course destination,Course Target) throws Exception {
        for (int i = 0; i < CourseList.size(); i++) {
            if (CourseList.get(i).getId() == destination.getId()){
                CourseList.remove(i);
            }
        }
        addCourse(Target);
    }
    public static List<Student> getStudentList(String studentList) throws IOException {
        // Creating an object of BufferedReader class
        // to read the file
        List<Student> students = new ArrayList<Student>();

        System.out.println("Student List: " );
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(new File(studentList)));
            // Reading the other lines of the file
            String line= " " ;
            String[] dataFields;
            int count=1;
            while ((line=fileReader.readLine()) != null) {
                Student s = new Student();
                dataFields = line.split(",");

                if (count > 1 && dataFields.length > 1) {
                    s.Id = Integer.parseInt(dataFields[0]);
                    s.name = dataFields[1];
                    s.Grade = Integer.parseInt(dataFields[2]);
                    s.Email = dataFields[3];
                    s.address = dataFields[4];
                    s.region = dataFields[5];
                    s.country = dataFields[6];
                    students.add(s);
                }
                count++;

            }
            fileReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public String toString() {
        return  Id +"\t" + name  +
                "\t" + Grade +
                "\t" + Id +
                "\t" + Email +
                "\t" + address +
                "\t" + region +
                "\t" + country ;
    }




    public static void getStudentCourseList() throws IOException {


        File dataList = new File( "C:\\Work Files\\Udacity Frontend Testing\\Trials\\LearningManagementSystem\\StudentCoursesList.json");
        PrintWriter writer = new PrintWriter(dataList);
        writer.println(" ");
        writer.close();
        dataList.getAbsolutePath();
    }
}


