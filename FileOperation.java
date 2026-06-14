// Create Interfaces bcz File I/O Operation can perform many different classes

import java.util.ArrayList;

// Interface that defines file input/output operations
public interface FileOperation {

    // Method to save student data into a file
    public void saveToFile(ArrayList<Student> students);

    // Method to read student data from a file
    public void readFromFile(ArrayList<Student> students);

}
