import java.io.*; // for input/output operations
import java.nio.file.*;
//import java.util.*;

public class FileUtil {
    private static final String FILE_NAME = "task.json"; // File to store tasks

    public static void FileExists() throws IOException { // Check if file exists, if not create it
        if(!Files.exists(Path.of(FILE_NAME))){
            Files.write(Path.of(FILE_NAME), "[]".getBytes());
        }
    }

    public static String readFile() throws IOException { // Read content from file
        FileExists(); // Ensure file exists
        return Files.readString(Path.of(FILE_NAME)); // read and return file content
    }

    public static void writeFile(String content) throws IOException { // Write content to file
        Files.write(Path.of(FILE_NAME), content.getBytes());
    }
    
}
