import java.io.*;

import java.util.*;

public class FileHandler implements FileOperation {

    // Save student data permanently into students.txt file
    @Override
    public void saveToFile(ArrayList<Student> students) {

        try {

            // Create BufferedWriter object for writing data into file
            BufferedWriter bw = new BufferedWriter(new FileWriter("students.txt"));

            // Traverse all student objects
            for (Student st : students) {

                // Store roll number and name separated by comma
                bw.write(st.getRollno() + "," + st.getName());

                // Move cursor to next line
                bw.newLine();

            }

            // Close file connection
            bw.close();

        } catch (Exception e) {

            System.out.println("Error While Saving!");
        }

    }

    // Read student data from file and load into ArrayList
    @Override
    public void readFromFile(ArrayList<Student> students) {

        try {

            // Create BufferedReader object for reading file
            BufferedReader br = new BufferedReader(new FileReader("students.txt"));

            String line;

            // Read file line by line until end of file
            while ((line = br.readLine()) != null) {

                /*
                 * We Split and Convert data bcz all data is
                 * stored as a string and we cannot perform operation
                 * if there is any integer,float etc...
                 */

                // Split Roll Number and Name
                String[] data = line.split(",");

                // Convert String Roll Number Into Integer Roll Number
                int roll = Integer.parseInt(data[0]);

                // Save Name Into a String Variable
                String name = data[1];

                // Create Student object using file data
                Student s = new Student(roll, name);

                // Store object into ArrayList
                students.add(s);
            }

            // Close file connection
            br.close();

        } catch (Exception e) {

            System.out.println("Error Loading File!");
        }

    }
}
