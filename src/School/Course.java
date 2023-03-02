package School;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private int id;
    private String CourseName;
    private String Instructor;
    private String CourseDuration;
    private String CourseTime;
    private String Location;

    @Override
    public String toString() {
        return  id +
                "\t"  + CourseName +
                "\t"  + Instructor +
                "\t"  + CourseDuration +
                "\t"  + CourseTime +
                "\t"  + Location;
    }
    public String toStringLine() {
        return  id +
                ","  + CourseName +
                ","  + Instructor +
                ","  + CourseDuration +
                ","  + CourseTime +
                ","  + Location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getInstructor() {
        return Instructor;
    }

    public void setInstructor(String instructor) {
        Instructor = instructor;
    }

    public String getCourseDuration() {
        return CourseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        CourseDuration = courseDuration;
    }

    public String getCourseTime() {
        return CourseTime;
    }

    public void setCourseTime(String courseTime) {
        CourseTime = courseTime;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public static List<Course> getCourseList() throws IOException {

        List<Course> CourseList = new ArrayList<Course>();

        // Creating an object of BufferedReader class
        // to read the file
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("CoursesList.csv"));
            String line= " " ;
            String[] dataFields;
            int count=1;
            while ((line=fileReader.readLine()) != null) {
                Course co = new Course();
                dataFields = line.split(",");

                if (dataFields.length > 1) {
                    co.id = Integer.parseInt(dataFields[0]);
                    co.CourseName = dataFields[1];
                    co.Instructor =dataFields[2];
                    co.CourseDuration = dataFields[3];
                    co.CourseTime = dataFields[4];
                    co.Location = dataFields[5];
                    CourseList.add(co);
                }
            }
            fileReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return CourseList;
    }



}
