package Lists;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import School.Course;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CourseList {


    public static List<Course> parseCourseList() throws IOException {
        List<Course> Courses = new ArrayList<Course>();
        // create a new document builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        // make the new document
        Document document = null;
        try {
            document = builder.parse(new File("courseData.xml"));
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        NodeList list = document.getElementsByTagName("row");

        for (int i = 0; i < list.getLength(); i++) {
            Course c = new Course();
            c.setId(Integer.parseInt(document.getElementsByTagName("id").item(i).getTextContent()));
            c.setCourseName(document.getElementsByTagName("CourseName").item(i).getTextContent());
            c.setInstructor(document.getElementsByTagName("Instructor").item(i).getTextContent());
            c.setCourseDuration(document.getElementsByTagName("CourseDuration").item(i).getTextContent());
            c.setCourseTime(document.getElementsByTagName("CourseTime").item(i).getTextContent());
            c.setLocation(document.getElementsByTagName("Location").item(i).getTextContent());
            Courses.add(c);
        }
        return Courses;
    }

    public static void buildCourseList() throws IOException {
        //Prints the finalized data into .csv file
        File dataList2 = new File("C:\\Work Files\\Udacity Frontend Testing\\Trials\\LearningManagementSystem\\CoursesList.csv");
        PrintWriter writer = new PrintWriter(dataList2);
        for (Course c:  parseCourseList()) {
            writer.println(c.toStringLine());
        }

        writer.close();
        dataList2.getAbsolutePath();
    }

}
