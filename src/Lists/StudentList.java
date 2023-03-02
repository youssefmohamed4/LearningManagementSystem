package Lists;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.io.File;

public class StudentList {
    // Reads the content of the .txt file and returns it as a string
    public static String getDataFile(File studentData) throws IOException {
        BufferedReader dataReader = new BufferedReader(new FileReader(studentData));
        StringBuilder dataAsString = new StringBuilder();

        String line;
        while ((line = dataReader.readLine()) != null) {
            dataAsString.append(line);
        }

        return dataAsString.toString();
    }

    // Takes the data in the form of a String and writes it to the CSV file with replacing needed characters
    public static void getdataList(String rawData) throws IOException {
        for (int i = 0; i < rawData.length(); i++) {
            rawData = rawData.replaceAll("#", ",");
            rawData = rawData.replaceAll("\\$", "\n");
        }
        File dataList = new File("C:\\Work Files\\Udacity Frontend Testing\\Trials\\LearningManagementSystem\\studentList.csv");
        PrintWriter writer = new PrintWriter(dataList);
        writer.println(rawData);
        writer.close();
        dataList.getAbsolutePath();

    }
}

